package com.example.data.model

enum class CropCategory(val displayName: String, val amharicName: String) {
    CEREAL("Cereals & Grains", "ጥራጥሬና እህል"),
    CASH_CROP("Cash Crops", "የገበያ ሰብል"),
    LEGUME("Pulses & Legumes", "አተርና ባቄላ"),
    VEGETABLE("Vegetables", "አትክልት"),
    ROOT_TUBER("Roots & Tubers", "ስራስርና ድንች")
}

data class Crop(
    val id: String,
    val nameEn: String,
    val nameAm: String,
    val scientificName: String,
    val category: CropCategory,
    val iconEmoji: String,
    val descriptionEn: String,
    val descriptionAm: String,
    val keyRegions: List<String>,
    val mainSeason: String,
    val commonDiseasesCount: Int
)
