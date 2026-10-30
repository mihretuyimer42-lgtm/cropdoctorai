package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.DiagnosisRecord
import com.example.data.model.UserRole
import com.example.ui.components.AppDestination
import com.example.ui.components.AppTopBar
import com.example.ui.components.CropDoctorBottomBar
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.CropDoctorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                val viewModel: CropDoctorViewModel = viewModel()
                val userSession by viewModel.userSession.collectAsStateWithLifecycle()
                val weather by viewModel.selectedWeather.collectAsStateWithLifecycle()
                val diagnoses by viewModel.allDiagnoses.collectAsStateWithLifecycle()
                val parcels by viewModel.allParcels.collectAsStateWithLifecycle()
                val consultations by viewModel.allConsultations.collectAsStateWithLifecycle()
                val scannerState by viewModel.scannerState.collectAsStateWithLifecycle()
                val chatMessages by viewModel.chatMessages.collectAsStateWithLifecycle()

                var currentDestination by remember { mutableStateOf(AppDestination.DASHBOARD) }
                var viewingDiagnosis by remember { mutableStateOf<DiagnosisRecord?>(null) }

                // Synchronize if scanner generates a new diagnosis
                LaunchedEffect(scannerState.latestDiagnosis) {
                    if (scannerState.latestDiagnosis != null) {
                        viewingDiagnosis = scannerState.latestDiagnosis
                    }
                }

                // Handle system back gesture
                BackHandler(enabled = viewingDiagnosis != null || currentDestination != AppDestination.DASHBOARD) {
                    if (viewingDiagnosis != null) {
                        viewingDiagnosis = null
                    } else if (currentDestination != AppDestination.DASHBOARD) {
                        currentDestination = AppDestination.DASHBOARD
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppTopBar(
                            userSession = userSession,
                            onRoleChanged = { viewModel.setRole(it) },
                            onLanguageToggled = { viewModel.toggleLanguage() },
                            onRegionSelected = { viewModel.setRegion(it) }
                        )
                    },
                    bottomBar = {
                        CropDoctorBottomBar(
                            currentDestination = currentDestination,
                            onNavigate = {
                                viewingDiagnosis = null
                                currentDestination = it
                            },
                            isAmharic = userSession.languageAmharic
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when {
                            // Detailed Diagnosis View
                            viewingDiagnosis != null -> {
                                DiagnosisDetailScreen(
                                    record = viewingDiagnosis!!,
                                    viewModel = viewModel,
                                    isAmharic = userSession.languageAmharic,
                                    onBack = { viewingDiagnosis = null },
                                    onEscalateToDA = {
                                        val rec = viewingDiagnosis!!
                                        viewModel.submitConsultation(
                                            crop = rec.cropName,
                                            title = "Escalated: ${rec.diseaseNameEn} (${rec.severity})",
                                            description = "AI detected ${rec.diseaseNameEn} with ${rec.confidence}% confidence. Visible symptoms: ${rec.symptomsSummary}. Requesting field DA verification."
                                        )
                                        viewingDiagnosis = null
                                        currentDestination = AppDestination.CONSULT
                                    }
                                )
                            }

                            // Role-specific Dashboards
                            userSession.role != UserRole.FARMER && currentDestination == AppDestination.DASHBOARD -> {
                                ExpertAdminDashboardScreen(
                                    userSession = userSession,
                                    diagnoses = diagnoses,
                                    consultations = consultations,
                                    onSwitchRole = { viewModel.setRole(it) },
                                    onNavigateToConsult = { currentDestination = AppDestination.CONSULT }
                                )
                            }

                            // Standard Nav Destinations
                            currentDestination == AppDestination.DASHBOARD -> {
                                FarmerDashboardScreen(
                                    userSession = userSession,
                                    weather = weather,
                                    diagnoses = diagnoses,
                                    parcels = parcels,
                                    onStartScan = { cropId ->
                                        viewModel.setScannerCrop(cropId)
                                        currentDestination = AppDestination.SCANNER
                                    },
                                    onViewDiagnosis = { viewingDiagnosis = it },
                                    onNavigateToFarms = { currentDestination = AppDestination.FARMS },
                                    onNavigateToKnowledge = { currentDestination = AppDestination.KNOWLEDGE },
                                    onNavigateToConsult = { currentDestination = AppDestination.CONSULT }
                                )
                            }

                            currentDestination == AppDestination.SCANNER -> {
                                PlantScannerScreen(
                                    viewModel = viewModel,
                                    scannerState = scannerState,
                                    isAmharic = userSession.languageAmharic,
                                    onDiagnosisComplete = {
                                        // Diagnosis will populate scannerState.latestDiagnosis which triggers viewingDiagnosis
                                    }
                                )
                            }

                            currentDestination == AppDestination.KNOWLEDGE -> {
                                KnowledgeCenterScreen(
                                    isAmharic = userSession.languageAmharic
                                )
                            }

                            currentDestination == AppDestination.FARMS -> {
                                FarmManagementScreen(
                                    viewModel = viewModel,
                                    parcels = parcels,
                                    isAmharic = userSession.languageAmharic
                                )
                            }

                            currentDestination == AppDestination.CONSULT -> {
                                ConsultationScreen(
                                    viewModel = viewModel,
                                    consultations = consultations,
                                    userSession = userSession
                                )
                            }

                            currentDestination == AppDestination.ASSISTANT -> {
                                AssistantChatScreen(
                                    viewModel = viewModel,
                                    chatMessages = chatMessages,
                                    isAmharic = userSession.languageAmharic
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
