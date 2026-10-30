package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.DiagnosisRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface DiagnosisDao {
    @Query("SELECT * FROM diagnosis_records ORDER BY timestamp DESC")
    fun getAllDiagnoses(): Flow<List<DiagnosisRecord>>

    @Query("SELECT * FROM diagnosis_records WHERE id = :id LIMIT 1")
    suspend fun getDiagnosisById(id: Long): DiagnosisRecord?

    @Query("SELECT * FROM diagnosis_records WHERE cropId = :cropId ORDER BY timestamp DESC")
    fun getDiagnosesByCrop(cropId: String): Flow<List<DiagnosisRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiagnosis(record: DiagnosisRecord): Long

    @Update
    suspend fun updateDiagnosis(record: DiagnosisRecord)

    @Query("DELETE FROM diagnosis_records WHERE id = :id")
    suspend fun deleteDiagnosis(id: Long)

    @Query("SELECT COUNT(*) FROM diagnosis_records")
    fun getDiagnosisCount(): Flow<Int>
}
