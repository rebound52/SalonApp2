package com.students.salonapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val service_id: String,
    val master_id: String,
    val user_id: String,
    val date: String,
    val time: String,
    val total_price: Double
)
