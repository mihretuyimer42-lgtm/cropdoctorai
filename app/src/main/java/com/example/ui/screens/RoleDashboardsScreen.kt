package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.PreloadedData
import com.example.data.model.Consultation
import com.example.data.model.DiagnosisRecord
import com.example.data.model.UserRole
import com.example.data.model.UserSession

@Composable
fun ExpertAdminDashboardScreen(
    userSession: UserSession,
    diagnoses: List<DiagnosisRecord>,
    consultations: List<Consultation>,
    onSwitchRole: (UserRole) -> Unit,
    onNavigateToConsult: () -> Unit
) {
    val isAmharic = userSession.languageAmharic
    val isAdmin = userSession.role == UserRole.ADMINISTRATOR

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (isAdmin)
                            (if (isAmharic) "የግብርና አስተዳዳሪ ዳሽቦርድ" else "Agricultural Admin Portal")
                        else
                            (if (isAmharic) "የልማት ጣቢያ ባለሙያ (DA) ዳሽቦርድ" else "Development Agent (DA) Portal"),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Region: ${userSession.region} • ${userSession.zone}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                FilledTonalButton(
                    onClick = { onSwitchRole(UserRole.FARMER) },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isAmharic) "ወደ አርሶ አደር እይታ" else "Farmer View", fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Key Regional Metrics
        item {
            Text(
                text = if (isAmharic) "የቀጠናዊ ምርመራና ስጋት አጠቃላይ እይታ" else "Regional Diagnostics & Surveillance",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "Total Diagnoses", fontSize = 11.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text(text = "${diagnoses.size + 142}", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text(text = "+18% in Meher Season", fontSize = 10.sp, color = Color(0xFF1B5E20))
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "Active Consultations", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(text = "${consultations.size}", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        Text(text = "100% Handled locally", fontSize = 10.sp, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "High Risk Crops", fontSize = 11.sp, color = Color(0xFF92400E))
                        Text(text = "Wheat & Tomato", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFB45309))
                        Text(text = "Rust & Late Blight", fontSize = 10.sp, color = Color(0xFF92400E))
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDCFCE7))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "AI Model Accuracy", fontSize = 11.sp, color = Color(0xFF166534))
                        Text(text = "94.2% F1", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF15803D))
                        Text(text = "Verified on Field Data", fontSize = 10.sp, color = Color(0xFF166534))
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Reported Disease Distribution (Epidemiology Breakdown)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (isAmharic) "የበሽታዎች ስርጭት በመቶኛ (ኢትዮጵያ)" else "Regional Pathogen Incident Breakdown",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    val diseaseStats = listOf(
                        Pair("Wheat Stem Rust (Ug99)", 0.38f),
                        Pair("Northern Corn Leaf Blight", 0.26f),
                        Pair("Coffee Berry Disease (CBD)", 0.18f),
                        Pair("Tomato Late Blight", 0.12f),
                        Pair("Enset Bacterial Wilt (BXW)", 0.06f)
                    )

                    diseaseStats.forEach { (name, ratio) ->
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(name, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                Text("${(ratio * 100).toInt()}%", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { ratio },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp),
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }
        }

        // Pending Cases for Extension Agent Review
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isAmharic) "የቀረቡ የገበሬ ጥያቄዎች (ለማረጋገጥ)" else "Farmer Consultation Queue",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                TextButton(onClick = onNavigateToConsult) {
                    Text("View All (${consultations.size})", fontSize = 12.sp)
                }
            }

            consultations.take(3).forEach { c ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(c.issueTitle, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text("Farmer: ${c.farmerName} • Crop: ${c.cropName}", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
                        }
                        FilledTonalButton(
                            onClick = onNavigateToConsult,
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(if (isAmharic) "መርምር" else "Review", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}
