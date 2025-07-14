package com.students.salonapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "services")
data class ServiceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val duration: Int,
    val price: Double,
    val image_url: String?,
    val category_id: String
)

