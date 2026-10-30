package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ConsultationStatus(val label: String, val amharicLabel: String) {
    PENDING("Awaiting Review", "መልስ በመጠባበቅ ላይ"),
    UNDER_REVIEW("Under Review by DA", "በግብርና ባለሙያ እየታየ ነው"),
    PRESCRIBED("Expert Prescribed", "መፍትሄ ተሰጥቷል"),
    RESOLVED("Resolved", "የተጠናቀቀ")
}

@Entity(tableName = "consultations")
data class Consultation(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val farmerName: String,
    val location: String,
    val cropName: String,
    val issueTitle: String,
    val description: String,
    val imageUri: String? = null,
    val aiSuggestedDisease: String? = null,
    val status: String = ConsultationStatus.PENDING.name,
    val expertName: String? = null,
    val expertResponse: String? = null,
    val prescribedDosage: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
