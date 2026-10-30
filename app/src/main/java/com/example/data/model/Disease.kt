package com.example.data.model

enum class PathogenType(val label: String, val amharicLabel: String) {
    FUNGAL("Fungal Disease", "ፈንገስ በሽታ"),
    BACTERIAL("Bacterial Infection", "ባክቴሪያ በሽታ"),
    VIRAL("Viral Disease", "ቫይረስ በሽታ"),
    PEST("Insect / Pest Infestation", "የነፍሳት / ተባይ ጉዳት"),
    HEALTHY("Healthy Plant", "ጤናማ ተክል")
}

enum class DiseaseSeverity(val label: String, val colorHex: Long) {
    HEALTHY("Healthy", 0xFF2E7D32),
    MILD("Mild (Early Stage)", 0xFFF59E0B),
    MODERATE("Moderate Risk", 0xFFE65100),
    SEVERE("Critical / Outbreak", 0xFFD32F2F)
}

enum class PlantOrgan(val labelEn: String, val labelAm: String) {
    LEAF("Leaf", "ቅጠል"),
    STEM("Stem / Stalk", "ግንድ"),
    FRUIT_HEAD("Fruit / Grain Head", "ፍሬ / ራስ"),
    WHOLE_PLANT("Whole Plant", "ሙሉ ተክል")
}

data class Disease(
    val id: String,
    val cropId: String,
    val nameEn: String,
    val nameAm: String,
    val scientificName: String,
    val pathogenType: PathogenType,
    val affectedOrgans: List<PlantOrgan>,
    val symptomsEn: List<String>,
    val symptomsAm: List<String>,
    val favorableConditions: String,
    val organicTreatmentsEn: List<String>,
    val organicTreatmentsAm: List<String>,
    val chemicalTreatmentsEn: List<String>,
    val chemicalTreatmentsAm: List<String>,
    val preventionMethodsEn: List<String>,
    val preventionMethodsAm: List<String>,
    val severityTypical: DiseaseSeverity,
    val daConsultationAdvised: Boolean = true
)
