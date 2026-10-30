package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnosis_records")
data class DiagnosisRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cropId: String,
    val cropName: String,
    val diseaseId: String,
    val diseaseNameEn: String,
    val diseaseNameAm: String,
    val scientificName: String,
    val confidence: Int, // e.g. 94 for 94%
    val severity: String, // HEALTHY, MILD, MODERATE, SEVERE
    val organ: String, // LEAF, STEM, FRUIT_HEAD, WHOLE_PLANT
    val imageUri: String?, // URI or sample asset
    val symptomsSummary: String,
    val recommendedTreatment: String,
    val timestamp: Long = System.currentTimeMillis(),
    val notes: String = "",
    val reviewedByExpert: Boolean = false,
    val expertFeedback: String? = null
)
