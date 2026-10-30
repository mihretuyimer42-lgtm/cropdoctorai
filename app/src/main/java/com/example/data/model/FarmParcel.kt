package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "farm_parcels")
data class FarmParcel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val parcelName: String,
    val region: String, // e.g. Oromia, Amhara, Sidama, SNNPR, Tigray
    val zone: String, // e.g. East Shewa, Jimma, Arsi, East Gojjam
    val areaHectares: Double, // in Hectares
    val activeCropName: String,
    val plantingDateEpoch: Long,
    val growthStage: String, // Germination, Vegetative, Flowering/Heading, Maturity, Harvest
    val lastFertilizerType: String = "NPS (100 kg/ha)",
    val lastIrrigationDateEpoch: Long = System.currentTimeMillis() - 86400000L * 3,
    val healthStatus: String = "Good",
    val notes: String = ""
)
