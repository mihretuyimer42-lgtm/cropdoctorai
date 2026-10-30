package com.example.data.model

data class KnowledgeArticle(
    val id: String,
    val titleEn: String,
    val titleAm: String,
    val cropName: String,
    val category: String, // Disease Management, Soil Fertility, Safe Pesticides, Organic Farming
    val readTimeMinutes: Int,
    val summaryEn: String,
    val summaryAm: String,
    val fullContentEn: String,
    val fullContentAm: String,
    val keyTakeawaysEn: List<String>,
    val keyTakeawaysAm: List<String>,
    val isOfflineReady: Boolean = true
)

data class EthiopianRegionWeather(
    val regionName: String,
    val zoneName: String,
    val seasonName: String, // Meher (ክረምት/መኸር), Belg (በልግ), Bega (በጋ)
    val temperatureC: Int,
    val condition: String,
    val humidityPercent: Int,
    val rainProbability: Int,
    val windKmh: Int,
    val fungalDiseaseRiskLevel: String, // Low, Moderate, High, Severe
    val advisoryNoticeEn: String,
    val advisoryNoticeAm: String
)

enum class UserRole(val title: String, val amharicTitle: String) {
    FARMER("Farmer", "አርሶ አደር"),
    EXPERT_DA("Development Agent (DA)", "የልማት ጣቢያ ባለሙያ"),
    ADMINISTRATOR("Agricultural Admin", "የግብርና አስተዳዳሪ")
}

data class UserSession(
    val role: UserRole = UserRole.FARMER,
    val name: String = "Abebe Bekele",
    val region: String = "Oromia",
    val zone: String = "Arsi Zone (Tiyo)",
    val primaryCrop: String = "Wheat & Teff",
    val languageAmharic: Boolean = false
)
