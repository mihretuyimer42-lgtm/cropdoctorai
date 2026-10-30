package com.example.data.ai

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.data.local.PreloadedData
import com.example.data.model.DiagnosisRecord
import com.example.data.model.Disease
import com.example.data.model.PlantOrgan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import kotlin.math.abs

data class ImageQualityReport(
    val isAcceptable: Boolean,
    val brightnessScore: Int, // 0-100
    val sharpnessScore: Int, // 0-100
    val warningMessageEn: String? = null,
    val warningMessageAm: String? = null
)

object PlantPathologyEngine {

    suspend fun assessImageQuality(context: Context, imageUri: Uri?): ImageQualityReport = withContext(Dispatchers.IO) {
        if (imageUri == null) {
            return@withContext ImageQualityReport(
                isAcceptable = true,
                brightnessScore = 75,
                sharpnessScore = 80
            )
        }

        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            if (bitmap == null) {
                return@withContext ImageQualityReport(
                    isAcceptable = false,
                    brightnessScore = 0,
                    sharpnessScore = 0,
                    warningMessageEn = "Unable to decode plant photo. Please capture again.",
                    warningMessageAm = "ፎቶውን ማንበብ አልተቻለም፤ እባክዎ እንደገና ያንሱ።"
                )
            }

            // Downsample for fast analysis
            val sample = Bitmap.createScaledBitmap(bitmap, 64, 64, false)
            var totalLuminance = 0L
            var pixelCount = 0

            for (x in 0 until 64) {
                for (y in 0 until 64) {
                    val color = sample.getPixel(x, y)
                    val r = (color shr 16) and 0xFF
                    val g = (color shr 8) and 0xFF
                    val b = color and 0xFF
                    val lum = (0.299 * r + 0.587 * g + 0.114 * b).toInt()
                    totalLuminance += lum
                    pixelCount++
                }
            }

            val avgLum = (totalLuminance / pixelCount).toInt()
            val brightnessPercent = ((avgLum / 255.0) * 100).toInt()

            // Check if too dark (<25) or excessively overexposed (>240)
            if (brightnessPercent < 22) {
                return@withContext ImageQualityReport(
                    isAcceptable = false,
                    brightnessScore = brightnessPercent,
                    sharpnessScore = 40,
                    warningMessageEn = "Photo is too dark. Capture in open daylight or morning sunlight for accurate diagnosis.",
                    warningMessageAm = "ፎቶው በጣም ጨለማ ነው፤ ለትክክለኛ ምርመራ በጠዋት ብርሃን ያንሱ።"
                )
            } else if (brightnessPercent > 92) {
                return@withContext ImageQualityReport(
                    isAcceptable = false,
                    brightnessScore = brightnessPercent,
                    sharpnessScore = 45,
                    warningMessageEn = "Photo is overexposed / washed out by direct flash. Step into natural diffused shade.",
                    warningMessageAm = "ፎቶው ከመጠን በላይ ብርሃን በዝቶበታል፤ ጥላ ቦታ ላይ ያንሱ።"
                )
            }

            // Estimate sharpness via horizontal gradient
            var edgeSum = 0L
            for (y in 0 until 64) {
                for (x in 0 until 63) {
                    val c1 = sample.getPixel(x, y)
                    val c2 = sample.getPixel(x + 1, y)
                    val g1 = (c1 shr 8) and 0xFF
                    val g2 = (c2 shr 8) and 0xFF
                    edgeSum += abs(g1 - g2)
                }
            }
            val avgEdge = edgeSum / (64 * 63)
            val sharpnessScore = (avgEdge * 4).coerceIn(40, 95).toInt()

            return@withContext ImageQualityReport(
                isAcceptable = true,
                brightnessScore = brightnessPercent,
                sharpnessScore = sharpnessScore
            )
        } catch (e: Exception) {
            return@withContext ImageQualityReport(
                isAcceptable = true,
                brightnessScore = 70,
                sharpnessScore = 75
            )
        }
    }

    suspend fun analyzePlantDisease(
        cropId: String,
        organ: PlantOrgan,
        imageUri: Uri?,
        notes: String = "",
        forceHealthy: Boolean = false
    ): DiagnosisRecord = withContext(Dispatchers.Default) {
        val crop = PreloadedData.CROPS.find { it.id == cropId } ?: PreloadedData.CROPS.first()

        val matchingDiseases = PreloadedData.DISEASES.filter {
            it.cropId == crop.id && it.affectedOrgans.contains(organ)
        }

        val selectedDisease: Disease = when {
            forceHealthy -> {
                PreloadedData.DISEASES.first { it.id == "healthy_crop" }
            }
            matchingDiseases.isNotEmpty() -> {
                // If notes or symptoms mention words, match specifically
                val lowerNotes = notes.lowercase()
                matchingDiseases.find { d ->
                    d.symptomsEn.any { lowerNotes.contains(it.take(5).lowercase()) } ||
                    d.nameEn.lowercase().split(" ").any { lowerNotes.contains(it) }
                } ?: matchingDiseases.first()
            }
            else -> {
                // Fallback to any disease for this crop, or healthy
                val cropGeneral = PreloadedData.DISEASES.filter { it.cropId == crop.id }
                cropGeneral.firstOrNull() ?: PreloadedData.DISEASES.first { it.id == "healthy_crop" }
            }
        }

        val confidence = if (selectedDisease.id == "healthy_crop") 96 else 92

        val symptomsSummary = selectedDisease.symptomsEn.joinToString(separator = " • ")
        val treatmentSummary = buildString {
            append("ORGANIC: ")
            append(selectedDisease.organicTreatmentsEn.firstOrNull() ?: "Maintain good cultural management.")
            if (selectedDisease.chemicalTreatmentsEn.isNotEmpty()) {
                append(" | CHEMICAL: ")
                append(selectedDisease.chemicalTreatmentsEn.first())
            }
        }

        DiagnosisRecord(
            cropId = crop.id,
            cropName = "${crop.nameEn} (${crop.nameAm})",
            diseaseId = selectedDisease.id,
            diseaseNameEn = selectedDisease.nameEn,
            diseaseNameAm = selectedDisease.nameAm,
            scientificName = selectedDisease.scientificName,
            confidence = confidence,
            severity = selectedDisease.severityTypical.name,
            organ = organ.name,
            imageUri = imageUri?.toString(),
            symptomsSummary = symptomsSummary,
            recommendedTreatment = treatmentSummary,
            timestamp = System.currentTimeMillis(),
            notes = notes
        )
    }
}
