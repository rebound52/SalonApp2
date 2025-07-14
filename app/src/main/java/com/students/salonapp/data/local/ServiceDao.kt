package com.students.salonapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: ServiceEntity): Long

    @Query("SELECT * FROM services ORDER BY name ASC")
    suspend fun getAllServices(): List<ServiceEntity>

    @Query("SELECT * FROM services WHERE category_id = :categoryId ORDER BY name ASC")
    suspend fun getServicesByCategory(categoryId: String): List<ServiceEntity>
}

