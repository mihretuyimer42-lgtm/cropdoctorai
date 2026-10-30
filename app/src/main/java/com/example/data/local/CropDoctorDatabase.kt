package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.Consultation
import com.example.data.model.DiagnosisRecord
import com.example.data.model.FarmParcel

@Database(
    entities = [DiagnosisRecord::class, FarmParcel::class, Consultation::class],
    version = 1,
    exportSchema = false
)
abstract class CropDoctorDatabase : RoomDatabase() {
    abstract fun diagnosisDao(): DiagnosisDao
    abstract fun farmParcelDao(): FarmParcelDao
    abstract fun consultationDao(): ConsultationDao

    companion object {
        @Volatile
        private var INSTANCE: CropDoctorDatabase? = null

        fun getDatabase(context: Context): CropDoctorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CropDoctorDatabase::class.java,
                    "cropdoctor_ai_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
