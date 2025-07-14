package com.students.salonapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: String,
    val name: String,
    val description: String,
    val duration: Int,
    val price: Double,
    val image_url: String?,
    val category_id: String
)
