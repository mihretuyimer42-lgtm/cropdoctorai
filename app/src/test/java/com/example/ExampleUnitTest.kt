package com.example

import com.example.data.ai.CropDoctorAssistantEngine
import com.example.data.ai.PlantPathologyEngine
import com.example.data.local.PreloadedData
import com.example.data.model.PlantOrgan
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testCropsCatalogInitialized() {
        val crops = PreloadedData.CROPS
        assertTrue("Ethiopian crops should be available", crops.isNotEmpty())
        assertNotNull(crops.find { it.id == "teff" })
        assertNotNull(crops.find { it.id == "wheat" })
        assertNotNull(crops.find { it.id == "coffee" })
        assertNotNull(crops.find { it.id == "enset" })
    }

    @Test
    fun testPlantPathologyEngineDiagnosis() = runBlocking {
        val diagnosis = PlantPathologyEngine.analyzePlantDisease(
            cropId = "wheat",
            organ = PlantOrgan.STEM,
            imageUri = null,
            notes = "reddish pustules"
        )
        assertNotNull(diagnosis)
        assertEquals("wheat", diagnosis.cropId)
        assertTrue(diagnosis.confidence >= 80)
        assertEquals("wheat_stem_rust", diagnosis.diseaseId)
    }

    @Test
    fun testAssistantAmharicResponse() {
        val response = CropDoctorAssistantEngine.generateAssistantResponse(
            query = "የስንዴ ዋግ በሽታ እንዴት ይከላከላል?",
            isAmharic = true
        )
        assertFalse(response.isUser)
        assertTrue(response.textAm.isNotEmpty())
        assertTrue(response.textAm.contains("ዋግ") || response.textAm.contains("ስንዴ"))
    }
}
