package com.example.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ai.ChatMessage
import com.example.data.ai.CropDoctorAssistantEngine
import com.example.data.ai.ImageQualityReport
import com.example.data.ai.PlantPathologyEngine
import com.example.data.local.CropDoctorDatabase
import com.example.data.local.PreloadedData
import com.example.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ScannerUiState(
    val selectedCropId: String = "maize",
    val selectedOrgan: PlantOrgan = PlantOrgan.LEAF,
    val imageUri: Uri? = null,
    val samplePhotoName: String? = null,
    val isAnalyzing: Boolean = false,
    val qualityReport: ImageQualityReport? = null,
    val latestDiagnosis: DiagnosisRecord? = null,
    val errorMessage: String? = null
)

class CropDoctorViewModel(application: Application) : AndroidViewModel(application) {

    private val db = CropDoctorDatabase.getDatabase(application)
    private val diagnosisDao = db.diagnosisDao()
    private val farmParcelDao = db.farmParcelDao()
    private val consultationDao = db.consultationDao()

    // User session & preferences
    private val _userSession = MutableStateFlow(
        UserSession(
            role = UserRole.FARMER,
            name = "Abebe Bekele",
            region = "Oromia",
            zone = "Arsi (Tiyo Woreda)",
            primaryCrop = "Wheat & Maize",
            languageAmharic = false
        )
    )
    val userSession: StateFlow<UserSession> = _userSession.asStateFlow()

    // Room Database Reactive Flows
    val allDiagnoses: StateFlow<List<DiagnosisRecord>> = diagnosisDao.getAllDiagnoses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allParcels: StateFlow<List<FarmParcel>> = farmParcelDao.getAllParcels()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allConsultations: StateFlow<List<Consultation>> = consultationDao.getAllConsultations()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Active Region Weather
    private val _selectedWeather = MutableStateFlow(PreloadedData.WEATHER_STATIONS.first())
    val selectedWeather: StateFlow<EthiopianRegionWeather> = _selectedWeather.asStateFlow()

    // Scanner State
    private val _scannerState = MutableStateFlow(ScannerUiState())
    val scannerState: StateFlow<ScannerUiState> = _scannerState.asStateFlow()

    // Chat Messages State
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                isUser = false,
                textEn = "Greetings! I am CropDoctor Assistant, your agricultural companion. How can I assist your farm today in Ethiopia? Ask about crop diseases, pest outbreaks, fertilizer dosage, or weather advisories.",
                textAm = "እንደምን አደሩ! እኔ ክሮፕዶክተር ረዳት ነኝ፤ የኢትዮጵያ አርሶ አደሮች የዲጂታል ባለሙያ። ዛሬ በምን ላግዝዎ? ስለ ሰብል በሽታዎች፣ አባጨጓሬና ተባይ፣ የማዳበሪያ መጠን ወይም የአየር ሁኔታ ይጠይቁኝ።"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    init {
        // Seed initial parcels and consultations if database is fresh
        viewModelScope.launch {
            farmParcelDao.getAllParcels().collect { parcels ->
                if (parcels.isEmpty()) {
                    PreloadedData.INITIAL_PARCELS.forEach { farmParcelDao.insertParcel(it) }
                }
            }
        }
        viewModelScope.launch {
            consultationDao.getAllConsultations().collect { consultations ->
                if (consultations.isEmpty()) {
                    PreloadedData.INITIAL_CONSULTATIONS.forEach { consultationDao.insertConsultation(it) }
                }
            }
        }
    }

    // Role, Region & Language Toggles
    fun setRole(role: UserRole) {
        _userSession.value = _userSession.value.copy(role = role)
    }

    fun toggleLanguage() {
        val current = _userSession.value.languageAmharic
        _userSession.value = _userSession.value.copy(languageAmharic = !current)
    }

    fun setRegion(regionName: String) {
        val matched = PreloadedData.WEATHER_STATIONS.find { it.regionName.equals(regionName, ignoreCase = true) }
            ?: PreloadedData.WEATHER_STATIONS.first()
        _selectedWeather.value = matched
        _userSession.value = _userSession.value.copy(region = matched.regionName, zone = matched.zoneName)
    }

    // Scanner actions
    fun setScannerCrop(cropId: String) {
        _scannerState.value = _scannerState.value.copy(selectedCropId = cropId)
    }

    fun setScannerOrgan(organ: PlantOrgan) {
        _scannerState.value = _scannerState.value.copy(selectedOrgan = organ)
    }

    fun setImageUri(uri: Uri?, sampleName: String? = null) {
        _scannerState.value = _scannerState.value.copy(
            imageUri = uri,
            samplePhotoName = sampleName,
            qualityReport = null,
            latestDiagnosis = null,
            errorMessage = null
        )

        // Evaluate image quality
        viewModelScope.launch {
            val report = PlantPathologyEngine.assessImageQuality(getApplication(), uri)
            _scannerState.value = _scannerState.value.copy(qualityReport = report)
        }
    }

    fun runDiagnosis(notes: String = "", forceHealthy: Boolean = false) {
        viewModelScope.launch {
            _scannerState.value = _scannerState.value.copy(isAnalyzing = true, errorMessage = null)
            try {
                // Short simulation pause for realistic diagnostic processing
                kotlinx.coroutines.delay(800)

                val result = PlantPathologyEngine.analyzePlantDisease(
                    cropId = _scannerState.value.selectedCropId,
                    organ = _scannerState.value.selectedOrgan,
                    imageUri = _scannerState.value.imageUri,
                    notes = notes,
                    forceHealthy = forceHealthy
                )

                // Persist diagnosis record into Room database
                val insertedId = diagnosisDao.insertDiagnosis(result)
                val savedRecord = result.copy(id = insertedId)

                _scannerState.value = _scannerState.value.copy(
                    isAnalyzing = false,
                    latestDiagnosis = savedRecord
                )
            } catch (e: Exception) {
                _scannerState.value = _scannerState.value.copy(
                    isAnalyzing = false,
                    errorMessage = e.localizedMessage ?: "Failed to complete diagnosis."
                )
            }
        }
    }

    fun deleteDiagnosis(id: Long) {
        viewModelScope.launch {
            diagnosisDao.deleteDiagnosis(id)
        }
    }

    // Farm Parcel actions
    fun addFarmParcel(name: String, region: String, zone: String, area: Double, crop: String) {
        viewModelScope.launch {
            val newParcel = FarmParcel(
                parcelName = name,
                region = region,
                zone = zone,
                areaHectares = area,
                activeCropName = crop,
                plantingDateEpoch = System.currentTimeMillis(),
                growthStage = "Germination / Emergence",
                lastFertilizerType = "NPS (100 kg/ha)",
                healthStatus = "Good",
                notes = "Registered parcel via CropDoctor Farm Manager"
            )
            farmParcelDao.insertParcel(newParcel)
        }
    }

    fun deleteParcel(id: Long) {
        viewModelScope.launch {
            farmParcelDao.deleteParcel(id)
        }
    }

    // Consultation actions
    fun submitConsultation(crop: String, title: String, description: String, imageUri: String? = null) {
        viewModelScope.launch {
            val ticket = Consultation(
                farmerName = _userSession.value.name,
                location = "${_userSession.value.region}, ${_userSession.value.zone}",
                cropName = crop,
                issueTitle = title,
                description = description,
                imageUri = imageUri,
                aiSuggestedDisease = _scannerState.value.latestDiagnosis?.diseaseNameEn,
                status = ConsultationStatus.PENDING.name,
                timestamp = System.currentTimeMillis()
            )
            consultationDao.insertConsultation(ticket)
        }
    }

    fun expertRespondConsultation(id: Long, expertName: String, prescription: String, dosage: String) {
        viewModelScope.launch {
            val existing = consultationDao.getConsultationById(id) ?: return@launch
            val updated = existing.copy(
                expertName = expertName,
                expertResponse = prescription,
                prescribedDosage = dosage,
                status = ConsultationStatus.PRESCRIBED.name
            )
            consultationDao.updateConsultation(updated)
        }
    }

    fun markConsultationResolved(id: Long) {
        viewModelScope.launch {
            val existing = consultationDao.getConsultationById(id) ?: return@launch
            val updated = existing.copy(status = ConsultationStatus.RESOLVED.name)
            consultationDao.updateConsultation(updated)
        }
    }

    // Chat Assistant actions
    fun sendUserMessage(query: String) {
        if (query.isBlank()) return
        val isAmharic = _userSession.value.languageAmharic
        val userMsg = ChatMessage(
            isUser = true,
            textEn = query,
            textAm = query
        )
        _chatMessages.value = _chatMessages.value + userMsg

        viewModelScope.launch {
            kotlinx.coroutines.delay(400) // slight conversational pacing
            val botMsg = CropDoctorAssistantEngine.generateAssistantResponse(query, isAmharic)
            _chatMessages.value = _chatMessages.value + botMsg
        }
    }
}
