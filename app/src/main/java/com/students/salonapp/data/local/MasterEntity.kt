package com.students.salonapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "masters")
data class MasterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val rating: Double,
    val bio: String,
    val image_url: String?
)

