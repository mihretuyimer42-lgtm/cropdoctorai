package com.example.ui.screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.R
import com.example.data.local.PreloadedData
import com.example.data.model.PlantOrgan
import com.example.viewmodel.CropDoctorViewModel
import com.example.viewmodel.ScannerUiState
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantScannerScreen(
    viewModel: CropDoctorViewModel,
    scannerState: ScannerUiState,
    isAmharic: Boolean,
    onDiagnosisComplete: () -> Unit
) {
    val context = LocalContext.current
    var farmerNotes by remember { mutableStateOf("") }
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            capturedBitmap = bitmap
            // Cache locally to get a file URI
            val file = File(context.cacheDir, "scan_${System.currentTimeMillis()}.jpg")
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            viewModel.setImageUri(Uri.fromFile(file), "Camera Photo")
        }
    }

    // Zero-permission Photo Picker
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            capturedBitmap = null
            viewModel.setImageUri(uri, "Gallery Image")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
    ) {
        // Title Header
        item {
            Text(
                text = if (isAmharic) "የእፅዋት በሽታ መመርመሪያ ስካነር" else "AI Plant Disease Scanner",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = if (isAmharic)
                    "የቅጠል ወይም የግንድ ፎቶ በማንሳት የበሽታውን አይነትና መፍትሄ በሰከንዶች ውስጥ ይወቁ"
                else
                    "Photograph or select plant tissue for laboratory-grade agronomic diagnosis",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )
        }

        // Camera / Image Preview Viewport
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (capturedBitmap != null) {
                        Image(
                            bitmap = capturedBitmap!!.asImageBitmap(),
                            contentDescription = "Captured Plant",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else if (scannerState.imageUri != null) {
                        AsyncImage(
                            model = scannerState.imageUri,
                            contentDescription = "Uploaded Plant",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        // Empty Viewport Placeholder with Guidance
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(54.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = if (isAmharic) "ፎቶ ያንሱ ወይም ከጋለሪ ይምረጡ" else "Take Plant Photo or Choose from Gallery",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isAmharic)
                                    "ቅጠሉን ወይም የታመመውን ክፍል በደንብ አቅርበው ያንሱ"
                                else
                                    "Hold camera 15-20cm from symptoms with clear daylight",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }

                    // Top label badge if sample is selected
                    scannerState.samplePhotoName?.let { sampleName ->
                        Surface(
                            color = Color.Black.copy(alpha = 0.65f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Specimen: $sampleName",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    // Retake overlay button if image exists
                    if (scannerState.imageUri != null || capturedBitmap != null) {
                        FilledTonalIconButton(
                            onClick = {
                                capturedBitmap = null
                                viewModel.setImageUri(null, null)
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons: Real Camera + Gallery Picker
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { cameraLauncher.launch(null) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("open_camera_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if (isAmharic) "ካሜራ ክፈት" else "Capture Photo")
                }

                OutlinedButton(
                    onClick = {
                        galleryLauncher.launch(
                            androidx.activity.result.PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("open_gallery_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.PhotoLibrary, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if (isAmharic) "ጋለሪ" else "Upload Gallery")
                }
            }
        }

        // Quality Report Banner if checked
        scannerState.qualityReport?.let { report ->
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (report.isAcceptable) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (report.isAcceptable) Icons.Default.CheckCircle else Icons.Default.Info,
                            contentDescription = null,
                            tint = if (report.isAcceptable) Color(0xFF2E7D32) else Color(0xFFD97706),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (report.isAcceptable)
                                    (if (isAmharic) "ጥሩ የፎቶ ጥራት • ለምርመራ ዝግጁ" else "Photo Quality: High Clarity")
                                else
                                    (if (isAmharic) (report.warningMessageAm ?: "የፎቶ ማስጠንቀቂያ") else (report.warningMessageEn ?: "Quality Warning")),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (report.isAcceptable) Color(0xFF1B5E20) else Color(0xFFB45309)
                            )
                            Text(
                                text = "Sharpness: ${report.sharpnessScore}% • Lighting: ${report.brightnessScore}%",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Quick Demonstration Specimens (so user can test all diseases immediately!)
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isAmharic) "ናሙና የእፅዋት በሽታዎች (ሙከራ)" else "Quick Test Field Specimens",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Tap to load",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            val sampleSpecimens = listOf(
                Triple("maize", PlantOrgan.LEAF, "Maize Leaf Blight"),
                Triple("wheat", PlantOrgan.STEM, "Wheat Stem Rust (Ug99)"),
                Triple("coffee", PlantOrgan.LEAF, "Coffee Leaf Rust"),
                Triple("enset", PlantOrgan.WHOLE_PLANT, "Enset Bacterial Wilt"),
                Triple("tomato", PlantOrgan.LEAF, "Tomato Late Blight"),
                Triple("teff", PlantOrgan.LEAF, "Teff Rust"),
                Triple("maize", PlantOrgan.LEAF, "Healthy Maize")
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(sampleSpecimens) { (cropId, organ, name) ->
                    val isSelected = scannerState.selectedCropId == cropId && scannerState.samplePhotoName == name
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            viewModel.setScannerCrop(cropId)
                            viewModel.setScannerOrgan(organ)
                            viewModel.setImageUri(null, name)
                        },
                        label = { Text(name, fontSize = 12.sp) },
                        leadingIcon = {
                            Icon(Icons.Default.Spa, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    )
                }
            }
        }

        // Step 1: Select Plant Organ
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isAmharic) "1. የተጠቃው የተክል ክፍል የትኛው ነው?" else "1. Which plant organ is affected?",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PlantOrgan.entries.forEach { organ ->
                    val isSelected = scannerState.selectedOrgan == organ
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { viewModel.setScannerOrgan(organ) },
                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                        border = if (isSelected)
                            CardDefaults.outlinedCardBorder()
                        else
                            androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = when (organ) {
                                    PlantOrgan.LEAF -> Icons.Default.Eco
                                    PlantOrgan.STEM -> Icons.Default.VerticalAlignTop
                                    PlantOrgan.FRUIT_HEAD -> Icons.Default.Grain
                                    PlantOrgan.WHOLE_PLANT -> Icons.Default.Nature
                                },
                                contentDescription = null,
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isAmharic) organ.labelAm else organ.labelEn,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Step 2: Crop Selector
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isAmharic) "2. የሰብል አይነት ይምረጡ" else "2. Select Crop Type",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(PreloadedData.CROPS) { crop ->
                    val isSelected = scannerState.selectedCropId == crop.id
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.setScannerCrop(crop.id) },
                        label = {
                            Text("${crop.iconEmoji} ${if (isAmharic) crop.nameAm else crop.nameEn}")
                        }
                    )
                }
            }
        }

        // Farmer Observation Notes (Optional)
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isAmharic) "ተጨማሪ ምልክቶች (አማራጭ)" else "Visible Symptoms or Observations (Optional)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = farmerNotes,
                onValueChange = { farmerNotes = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("symptoms_notes_input"),
                placeholder = {
                    Text(
                        if (isAmharic) "ለምሳሌ፡ ቡናማ ነጠብጣቦች፣ መጥወልወል፣ ከዝናብ በኋላ የታየ..." else "e.g. reddish pustules after rain, yellow halo, wilting heart leaf...",
                        fontSize = 12.sp
                    )
                },
                maxLines = 3,
                shape = RoundedCornerShape(12.dp)
            )
        }

        // Primary Action: Run Diagnosis
        item {
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val isHealthyCheck = scannerState.samplePhotoName?.contains("Healthy", ignoreCase = true) == true
                    viewModel.runDiagnosis(notes = farmerNotes, forceHealthy = isHealthyCheck)
                    onDiagnosisComplete()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("run_diagnosis_btn"),
                shape = RoundedCornerShape(14.dp),
                enabled = !scannerState.isAnalyzing
            ) {
                if (scannerState.isAnalyzing) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(if (isAmharic) "በመመርመር ላይ..." else "Running AI Pathology Scan...")
                } else {
                    Icon(Icons.Default.Biotech, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isAmharic) "የእፅዋት በሽታውን መርምር" else "Analyze Crop Disease",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
