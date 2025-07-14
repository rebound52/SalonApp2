package com.students.salonapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Master(
    val id: String,
    val name: String,
    val rating: Double,
    val bio: String,
    val image_url: String?
)
