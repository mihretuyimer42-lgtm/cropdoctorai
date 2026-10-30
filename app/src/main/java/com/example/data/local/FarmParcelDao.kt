package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.FarmParcel
import kotlinx.coroutines.flow.Flow

@Dao
interface FarmParcelDao {
    @Query("SELECT * FROM farm_parcels ORDER BY id DESC")
    fun getAllParcels(): Flow<List<FarmParcel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParcel(parcel: FarmParcel): Long

    @Update
    suspend fun updateParcel(parcel: FarmParcel)

    @Query("DELETE FROM farm_parcels WHERE id = :id")
    suspend fun deleteParcel(id: Long)

    @Query("SELECT COUNT(*) FROM farm_parcels")
    fun getParcelCount(): Flow<Int>
}
