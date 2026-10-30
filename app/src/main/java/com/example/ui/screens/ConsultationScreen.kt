package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Consultation
import com.example.data.model.ConsultationStatus
import com.example.data.model.UserRole
import com.example.data.model.UserSession
import com.example.viewmodel.CropDoctorViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationScreen(
    viewModel: CropDoctorViewModel,
    consultations: List<Consultation>,
    userSession: UserSession
) {
    val isAmharic = userSession.languageAmharic
    var showCreateDialog by remember { mutableStateOf(false) }
    var selectedConsultation by remember { mutableStateOf<Consultation?>(null) }

    // New Ticket Fields
    var ticketCrop by remember { mutableStateOf("Wheat") }
    var ticketTitle by remember { mutableStateOf("") }
    var ticketDescription by remember { mutableStateOf("") }

    // Expert Response Fields (for DA role)
    var expertPrescription by remember { mutableStateOf("") }
    var expertDosage by remember { mutableStateOf("") }

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
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isAmharic) "የግብርና ባለሙያ ማማከሪያ" else "Expert Consultation Center",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = if (isAmharic)
                            "ከተመሰከረላቸው የልማት ጣቢያ ባለሙያዎችና ተመራማሪዎች ጋር ይገናኙ"
                        else
                            "Connect with certified Woreda Development Agents (DAs) & Pathologists",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Button(
                    onClick = { showCreateDialog = true },
                    modifier = Modifier.testTag("new_consultation_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isAmharic) "አዲስ ጥያቄ" else "New Case")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Cases List
        if (consultations.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContactSupport,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isAmharic) "እስካሁን የቀረበ የባለሙያ ጥያቄ የለም" else "No consultation tickets yet",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = if (isAmharic)
                                "ሰብልዎ ላይ እንግዳ ምልክት ካዩ የባለሙያ እገዛ ለመጠየቅ ከላይ ያለውን ይጫኑ"
                            else
                                "Tap 'New Case' above to request human expert diagnosis on your field",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        } else {
            items(consultations) { caseItem ->
                val statusEnum = try {
                    ConsultationStatus.valueOf(caseItem.status)
                } catch (e: Exception) {
                    ConsultationStatus.PENDING
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { selectedConsultation = caseItem }
                        .testTag("consultation_ticket_${caseItem.id}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Text(
                                    text = caseItem.cropName,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            // Status Chip
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = when (statusEnum) {
                                    ConsultationStatus.PENDING -> Color(0xFFFFF3E0)
                                    ConsultationStatus.UNDER_REVIEW -> Color(0xFFE0F2FE)
                                    ConsultationStatus.PRESCRIBED -> Color(0xFFE8F5E9)
                                    ConsultationStatus.RESOLVED -> Color(0xFFF3F4F6)
                                }
                            ) {
                                Text(
                                    text = if (isAmharic) statusEnum.amharicLabel else statusEnum.label,
                                    color = when (statusEnum) {
                                        ConsultationStatus.PENDING -> Color(0xFFD97706)
                                        ConsultationStatus.UNDER_REVIEW -> Color(0xFF0284C7)
                                        ConsultationStatus.PRESCRIBED -> Color(0xFF2E7D32)
                                        ConsultationStatus.RESOLVED -> Color(0xFF4B5563)
                                    },
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = caseItem.issueTitle,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = caseItem.description,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        caseItem.expertResponse?.let { resp ->
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFFF0FDF4),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Verified,
                                            contentDescription = null,
                                            tint = Color(0xFF16A34A),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = caseItem.expertName ?: "Agricultural DA",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = Color(0xFF15803D)
                                        )
                                    }
                                    Text(
                                        text = resp,
                                        fontSize = 12.sp,
                                        color = Color(0xFF166534),
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                    caseItem.prescribedDosage?.let { dosage ->
                                        Text(
                                            text = "Prescribed Dosage: $dosage",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF15803D),
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Farmer: ${caseItem.farmerName} • ${caseItem.location}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            val df = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                            Text(
                                text = df.format(Date(caseItem.timestamp)),
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }
        }
    }

    // Create Case Dialog
    if (showCreateDialog) {
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = {
                Text(if (isAmharic) "የባለሙያ ማማከሪያ ጥያቄ ያቅርቡ" else "Submit Consultation Request")
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = ticketCrop,
                        onValueChange = { ticketCrop = it },
                        label = { Text(if (isAmharic) "የሰብል አይነት" else "Affected Crop") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = ticketTitle,
                        onValueChange = { ticketTitle = it },
                        label = { Text(if (isAmharic) "የችግሩ አርዕስት" else "Issue Title") },
                        placeholder = { Text("e.g. Red spots on wheat stems") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = ticketDescription,
                        onValueChange = { ticketDescription = it },
                        label = { Text(if (isAmharic) "ዝርዝር ማብራሪያ" else "Detailed Description") },
                        placeholder = { Text("Describe observed symptoms, recent rains, fertilizers used...") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 4
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (ticketTitle.isNotBlank()) {
                            viewModel.submitConsultation(
                                crop = ticketCrop,
                                title = ticketTitle,
                                description = ticketDescription
                            )
                            showCreateDialog = false
                            ticketTitle = ""
                            ticketDescription = ""
                        }
                    }
                ) {
                    Text(if (isAmharic) "ላክ" else "Submit Request")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text(if (isAmharic) "ሰርዝ" else "Cancel")
                }
            }
        )
    }

    // Case Detail / DA Action Dialog
    selectedConsultation?.let { c ->
        AlertDialog(
            onDismissRequest = { selectedConsultation = null },
            title = {
                Text(c.issueTitle, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            },
            text = {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        Text(
                            text = "Crop: ${c.cropName} • Farmer: ${c.farmerName} (${c.location})",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(c.description, fontSize = 13.sp, lineHeight = 19.sp)

                        c.expertResponse?.let { resp ->
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Expert DA Prescription:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color(0xFF15803D)
                            )
                            Text(resp, fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp))
                            c.prescribedDosage?.let { dosage ->
                                Text("Dosage: $dosage", fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = Color(0xFF15803D))
                            }
                        }

                        // If user is currently in Expert DA Role, allow writing prescription directly!
                        if (userSession.role == UserRole.EXPERT_DA) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Expert Actions (Development Agent):",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            OutlinedTextField(
                                value = expertPrescription,
                                onValueChange = { expertPrescription = it },
                                label = { Text("Professional Recommendation") },
                                placeholder = { Text("e.g. Verified Rust. Apply Folicur 250 EC before 10 AM.") },
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 3
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = expertDosage,
                                onValueChange = { expertDosage = it },
                                label = { Text("Prescribed Dosage") },
                                placeholder = { Text("e.g. 500 ml/ha in 200 L water") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            },
            confirmButton = {
                if (userSession.role == UserRole.EXPERT_DA) {
                    Button(
                        onClick = {
                            viewModel.expertRespondConsultation(
                                id = c.id,
                                expertName = userSession.name,
                                prescription = if (expertPrescription.isNotBlank()) expertPrescription else "Verified diagnosis. Follow approved extension protocols.",
                                dosage = if (expertDosage.isNotBlank()) expertDosage else "Standard extension rate"
                            )
                            selectedConsultation = null
                        }
                    ) {
                        Text("Sign & Prescribe")
                    }
                } else {
                    Button(onClick = { selectedConsultation = null }) {
                        Text(if (isAmharic) "ዝጋ" else "Close")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { selectedConsultation = null }) {
                    Text(if (isAmharic) "ሰርዝ" else "Cancel")
                }
            }
        )
    }
}
