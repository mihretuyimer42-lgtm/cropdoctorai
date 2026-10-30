package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.PreloadedData
import com.example.data.model.DiagnosisRecord
import com.example.data.model.Disease
import com.example.data.model.DiseaseSeverity
import com.example.viewmodel.CropDoctorViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DiagnosisDetailScreen(
    record: DiagnosisRecord,
    viewModel: CropDoctorViewModel,
    isAmharic: Boolean,
    onBack: () -> Unit,
    onEscalateToDA: () -> Unit
) {
    var isPlayingAudio by remember { mutableStateOf(false) }

    val disease: Disease? = remember(record.diseaseId) {
        PreloadedData.DISEASES.find { it.id == record.diseaseId }
    }

    val severityColor = when (record.severity.uppercase()) {
        "SEVERE" -> Color(0xFFDC2626)
        "MODERATE" -> Color(0xFFE65100)
        "MILD" -> Color(0xFFF59E0B)
        else -> Color(0xFF2E7D32)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
    ) {
        // Top Back Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("detail_back_btn")
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = if (isAmharic) "የምርመራ ውጤት" else "Diagnosis Report",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                IconButton(onClick = { viewModel.deleteDiagnosis(record.id); onBack() }) {
                    Icon(Icons.Default.DeleteOutline, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Primary Header Card: Disease Name, Confidence, Severity
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(3.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = record.cropName,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = if (isAmharic) record.diseaseNameAm else record.diseaseNameEn,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = record.scientificName,
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        // Confidence Gauge Circle
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${record.confidence}%",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Text(
                                text = if (isAmharic) "እርግጠኝነት" else "Confidence",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.outline,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Severity banner
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = severityColor.copy(alpha = 0.12f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (record.severity.uppercase() == "HEALTHY")
                                    Icons.Default.CheckCircle else Icons.Default.Warning,
                                contentDescription = null,
                                tint = severityColor,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isAmharic)
                                    "የበሽታው ደረጃ፡ ${record.severity}"
                                else
                                    "Severity Level: ${record.severity} (${record.organ} Organ)",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = severityColor
                            )
                        }
                    }

                    // Audio Read Aloud Accessibility Bar
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = { isPlayingAudio = !isPlayingAudio },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = if (isPlayingAudio) Icons.Default.VolumeUp else Icons.Default.VolumeMute,
                            contentDescription = "Read Aloud",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isPlayingAudio)
                                (if (isAmharic) "ድምጽ በማጫወት ላይ (አቁም)..." else "Playing Audio Guidance (Stop)...")
                            else
                                (if (isAmharic) "የምርመራውን ውጤት በድምጽ ያዳምጡ (ለአርሶ አደር)" else "Listen to Diagnosis Aloud (Spoken Audio)")
                        )
                    }

                    if (isPlayingAudio) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = if (isAmharic)
                                    "ድምጽ፡ '${record.diseaseNameAm}' ተገኝቷል። የተመከረ መፍትሄ፡ ${record.recommendedTreatment}"
                                else
                                    "Audio: '${record.diseaseNameEn}' detected with ${record.confidence}% confidence. Treatment: ${record.recommendedTreatment}",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(10.dp),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }

        // Identified Symptoms
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isAmharic) "የታዩ ምልክቶች" else "Clinical Symptoms Identified",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            val symptomsList = if (disease != null) {
                if (isAmharic) disease.symptomsAm else disease.symptomsEn
            } else {
                listOf(record.symptomsSummary)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    symptomsList.forEach { symptom ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.FiberManualRecord,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .size(10.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = symptom,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }

        // Treatment & Control Measures (Organic vs Chemical)
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = if (isAmharic) "የመከላከያና የማከሚያ መንገዶች" else "Recommended Treatment Protocol",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Organic / Cultural Methods
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Forest,
                            contentDescription = null,
                            tint = Color(0xFF2E7D32),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isAmharic) "የተፈጥሮና የባህል አያያዝ ዘዴዎች" else "Organic & Cultural Management",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF1B5E20)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    val organicList = if (disease != null) {
                        if (isAmharic) disease.organicTreatmentsAm else disease.organicTreatmentsEn
                    } else {
                        listOf("Rotate crops and destroy infected crop residue.")
                    }

                    organicList.forEach { treatment ->
                        Row(modifier = Modifier.padding(vertical = 3.dp)) {
                            Text("✓ ", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                            Text(
                                text = treatment,
                                fontSize = 13.sp,
                                color = Color(0xFF1B5E20),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Chemical Fungicide / Pesticide
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Vaccines,
                            contentDescription = null,
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isAmharic) "የኬሚካል ፀረ-ፈንገስ / ፀረ-ተባይ አጠቃቀም" else "Chemical Control (Ethiopian MoA Approved)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFFB45309)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    val chemList = if (disease != null) {
                        if (isAmharic) disease.chemicalTreatmentsAm else disease.chemicalTreatmentsEn
                    } else {
                        listOf("Consult local Development Agent for registered chemicals.")
                    }

                    chemList.forEach { chem ->
                        Row(modifier = Modifier.padding(vertical = 3.dp)) {
                            Text("⚠️ ", fontSize = 12.sp)
                            Text(
                                text = chem,
                                fontSize = 13.sp,
                                color = Color(0xFF78350F),
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }

        // Development Agent (DA) Consultation Trigger
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isAmharic) "የግብርና ባለሙያ ማማከር ይፈልጋሉ?" else "Need Extension Agent (DA) Verification?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = if (isAmharic)
                            "የምርመራውን ውጤት በቀጥታ ለቀበሌዎ የልማት ጣቢያ ባለሙያ ልከው የተረጋገጠ ማዘዣ ያግኙ"
                        else
                            "Submit this AI diagnosis to your Woreda Development Agent for human verification and official prescription",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Button(
                        onClick = onEscalateToDA,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("escalate_to_da_btn"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.SupportAgent, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isAmharic) "ለግብርና ባለሙያ ላክ" else "Send Case to Agricultural Expert")
                    }
                }
            }
        }
    }
}
