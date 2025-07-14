package com.students.salonapp.data.uiState

import com.students.salonapp.data.models.Booking

sealed class BookingUiState {
    object Idle : BookingUiState()
    object Loading : BookingUiState()
    data class Success(val booking: Booking) : BookingUiState()
    data class Error(val message: String) : BookingUiState()
}
