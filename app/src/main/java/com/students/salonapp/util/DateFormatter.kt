package com.students.salonapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val dayMonthFormatter = DateTimeFormatter.ofPattern("dd MMM") // "15 Jun"

    fun formatDate(date: LocalDate): String {
        return date.format(dayMonthFormatter)
    }
}
