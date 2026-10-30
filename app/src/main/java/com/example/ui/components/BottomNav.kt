package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class AppDestination(
    val titleEn: String,
    val titleAm: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    DASHBOARD("Home", "መነሻ", Icons.Filled.Home, Icons.Outlined.Home, "nav_dashboard"),
    SCANNER("Diagnose", "መርምር", Icons.Filled.CameraAlt, Icons.Outlined.CameraAlt, "nav_scanner"),
    KNOWLEDGE("Guides", "መመሪያ", Icons.Filled.MenuBook, Icons.Outlined.MenuBook, "nav_knowledge"),
    FARMS("Farms", "ማሳ", Icons.Filled.Yard, Icons.Outlined.Yard, "nav_farms"),
    CONSULT("Expert", "ባለሙያ", Icons.Filled.SupportAgent, Icons.Outlined.SupportAgent, "nav_consult"),
    ASSISTANT("Assistant", "ረዳት", Icons.Filled.SmartToy, Icons.Outlined.SmartToy, "nav_assistant")
}

@Composable
fun CropDoctorBottomBar(
    currentDestination: AppDestination,
    onNavigate: (AppDestination) -> Unit,
    isAmharic: Boolean
) {
    NavigationBar(
        windowInsets = WindowInsets.navigationBars,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {
        AppDestination.entries.forEach { destination ->
            val isSelected = currentDestination == destination
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(destination) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = destination.titleEn,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = if (isAmharic) destination.titleAm else destination.titleEn,
                        fontSize = 10.sp,
                        maxLines = 1
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag(destination.testTag)
            )
        }
    }
}
