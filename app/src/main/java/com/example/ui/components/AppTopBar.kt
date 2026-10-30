package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.PreloadedData
import com.example.data.model.UserRole
import com.example.data.model.UserSession

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    userSession: UserSession,
    onRoleChanged: (UserRole) -> Unit,
    onLanguageToggled: () -> Unit,
    onRegionSelected: (String) -> Unit
) {
    var showRoleMenu by remember { mutableStateOf(false) }
    var showRegionMenu by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Logo & App Name
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cropdoctor_logo),
                        contentDescription = "CropDoctor Logo",
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (userSession.languageAmharic) "ክሮፕዶክተር AI" else "CropDoctor AI",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = if (userSession.languageAmharic) "የእርስዎ የሰብል ሀኪም" else "AI Plant Pathologist",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.82f)
                        )
                    }
                }

                // Controls: Language Toggle & Role Switcher
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Language Switcher Chip
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onLanguageToggled() }
                            .testTag("lang_toggle_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Translate,
                                contentDescription = "Language",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (userSession.languageAmharic) "አማ" else "EN",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    // Role Switcher Pill
                    Box {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { showRoleMenu = true }
                                .testTag("role_switcher_pill")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = when (userSession.role) {
                                        UserRole.FARMER -> Icons.Default.Agriculture
                                        UserRole.EXPERT_DA -> Icons.Default.Verified
                                        UserRole.ADMINISTRATOR -> Icons.Default.AdminPanelSettings
                                    },
                                    contentDescription = "Role",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (userSession.languageAmharic) userSession.role.amharicTitle else userSession.role.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Expand",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = showRoleMenu,
                            onDismissRequest = { showRoleMenu = false }
                        ) {
                            UserRole.entries.forEach { role ->
                                DropdownMenuItem(
                                    text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = when (role) {
                                                    UserRole.FARMER -> Icons.Default.Agriculture
                                                    UserRole.EXPERT_DA -> Icons.Default.Verified
                                                    UserRole.ADMINISTRATOR -> Icons.Default.AdminPanelSettings
                                                },
                                                contentDescription = null,
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Column {
                                                Text(role.title, fontWeight = FontWeight.SemiBold)
                                                Text(role.amharicTitle, fontSize = 11.sp, color = MaterialTheme.colorScheme.outline)
                                            }
                                        }
                                    },
                                    onClick = {
                                        onRoleChanged(role)
                                        showRoleMenu = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Sub-bar: Region pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { showRegionMenu = true }
                    .padding(vertical = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${userSession.region} • ${userSession.zone}",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Choose Region",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(14.dp)
                )

                DropdownMenu(
                    expanded = showRegionMenu,
                    onDismissRequest = { showRegionMenu = false }
                ) {
                    PreloadedData.WEATHER_STATIONS.forEach { station ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(
                                        text = "${station.regionName} (${station.zoneName})",
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "Season: ${station.seasonName} • Risk: ${station.fungalDiseaseRiskLevel}",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.outline
                                    )
                                }
                            },
                            onClick = {
                                onRegionSelected(station.regionName)
                                showRegionMenu = false
                            }
                        )
                    }
                }
            }
        }
    }
}
