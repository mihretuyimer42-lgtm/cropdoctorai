package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.Consultation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConsultationDao {
    @Query("SELECT * FROM consultations ORDER BY timestamp DESC")
    fun getAllConsultations(): Flow<List<Consultation>>

    @Query("SELECT * FROM consultations WHERE id = :id LIMIT 1")
    suspend fun getConsultationById(id: Long): Consultation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsultation(consultation: Consultation): Long

    @Update
    suspend fun updateConsultation(consultation: Consultation)

    @Query("DELETE FROM consultations WHERE id = :id")
    suspend fun deleteConsultation(id: Long)
}
