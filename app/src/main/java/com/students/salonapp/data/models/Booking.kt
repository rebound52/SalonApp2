package com.students.salonapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    val id: String,
    val service_id: String,
    val master_id: String,
    val user_id: String,
    val date: String,
    val time: String,
    val total_price: Double
)

@Serializable
data class BookingCreateRequest(
    val service_id: String,
    val master_id: String,
    val user_id: String,
    val date: String,
    val time: String,
    val total_price: Double
)
