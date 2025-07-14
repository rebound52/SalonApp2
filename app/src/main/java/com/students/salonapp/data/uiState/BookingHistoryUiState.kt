package com.students.salonapp.data.uiState

import com.students.salonapp.data.models.Booking

sealed class BookingHistoryUiState {
    object Idle : BookingHistoryUiState()
    object Loading : BookingHistoryUiState()
    data class Success(val bookings: List<Booking>) : BookingHistoryUiState()
    data class Error(val message: String) : BookingHistoryUiState()
}

