package com.students.salonapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String,
    val title: String
)
