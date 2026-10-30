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
import com.example.data.local.PreloadedData
import com.example.data.model.FarmParcel
import com.example.viewmodel.CropDoctorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmManagementScreen(
    viewModel: CropDoctorViewModel,
    parcels: List<FarmParcel>,
    isAmharic: Boolean
) {
    var showAddDialog by remember { mutableStateOf(false) }

    var newParcelName by remember { mutableStateOf("") }
    var newParcelArea by remember { mutableStateOf("1.5") }
    var newParcelRegion by remember { mutableStateOf("Oromia") }
    var newParcelZone by remember { mutableStateOf("East Shewa") }
    var newParcelCrop by remember { mutableStateOf("Teff (Quncho)") }

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
                        text = if (isAmharic) "የማሳ አስተዳደር" else "Farm & Parcel Manager",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = if (isAmharic) "የእርሻ መሬት፣ የሰብል ደረጃና የማዳበሪያ መዝገብ" else "Track crop parcels, growth stages & fertilizer inputs",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                FilledTonalButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier.testTag("add_parcel_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isAmharic) "አዲስ ማሳ" else "Add Parcel")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Summary Stats Grid
        item {
            val totalHa = parcels.sumOf { it.areaHectares }
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
                        Text(
                            text = if (isAmharic) "ጠቅላላ ስፋት" else "Total Area",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "%.2f Ha".format(totalHa),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "(~%.1f Qada)".format(totalHa * 4),
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                }

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = if (isAmharic) "ንቁ ማሳዎች" else "Active Parcels",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${parcels.size} Fields",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "100% Monitored",
                            fontSize = 11.sp,
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Parcels List
        items(parcels) { parcel ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .testTag("parcel_card_${parcel.id}"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = parcel.parcelName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${parcel.region} • ${parcel.zone}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = when (parcel.healthStatus.lowercase()) {
                                "good", "excellent" -> Color(0xFFE8F5E9)
                                else -> Color(0xFFFFF3E0)
                            }
                        ) {
                            Text(
                                text = parcel.healthStatus,
                                color = when (parcel.healthStatus.lowercase()) {
                                    "good", "excellent" -> Color(0xFF2E7D32)
                                    else -> Color(0xFFE65100)
                                },
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = if (isAmharic) "የተዘራው ሰብል" else "Cultivated Crop",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = parcel.activeCropName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Column {
                            Text(
                                text = if (isAmharic) "ስፋት" else "Area",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = "%.2f Hectares".format(parcel.areaHectares),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Column {
                            Text(
                                text = if (isAmharic) "የእድገት ደረጃ" else "Stage",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = parcel.growthStage,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Science,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Fertilizer: ${parcel.lastFertilizerType}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }

    // Add Parcel Dialog
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = {
                Text(if (isAmharic) "አዲስ ማሳ ይመዝግቡ" else "Register New Farm Parcel")
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = newParcelName,
                        onValueChange = { newParcelName = it },
                        label = { Text(if (isAmharic) "የማሳው ስም" else "Parcel Name") },
                        placeholder = { Text("e.g. Asella Upper Field") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newParcelArea,
                        onValueChange = { newParcelArea = it },
                        label = { Text(if (isAmharic) "ስፋት በሄክታር" else "Area (Hectares)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newParcelCrop,
                        onValueChange = { newParcelCrop = it },
                        label = { Text(if (isAmharic) "ሰብል" else "Primary Crop") },
                        placeholder = { Text("e.g. Wheat (Kakaba)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newParcelZone,
                        onValueChange = { newParcelZone = it },
                        label = { Text(if (isAmharic) "ዞን / ወረዳ" else "Zone / Woreda") },
                        placeholder = { Text("e.g. Arsi Tiyo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val area = newParcelArea.toDoubleOrNull() ?: 1.0
                        val name = if (newParcelName.isNotBlank()) newParcelName else "Parcel ${parcels.size + 1}"
                        viewModel.addFarmParcel(
                            name = name,
                            region = newParcelRegion,
                            zone = newParcelZone,
                            area = area,
                            crop = newParcelCrop
                        )
                        showAddDialog = false
                    }
                ) {
                    Text(if (isAmharic) "መዝግብ" else "Save Parcel")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text(if (isAmharic) "ሰርዝ" else "Cancel")
                }
            }
        )
    }
}
