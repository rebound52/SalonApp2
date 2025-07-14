package com.students.salonapp.data.models

data class Promo(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val discount: Int = 0,
    val endDate: String = "",
    val conditions: String = ""
) 