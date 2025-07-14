package com.students.salonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.students.salonapp.data.models.Booking
import com.students.salonapp.data.models.BookingCreateRequest
import com.students.salonapp.data.supabase.SupabaseRepository
import com.students.salonapp.data.uiState.BookingHistoryUiState
import com.students.salonapp.data.uiState.BookingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val repository: SupabaseRepository = SupabaseRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookingUiState>(BookingUiState.Idle)
    val uiState: StateFlow<BookingUiState> = _uiState

    private val _historyUiState = MutableStateFlow<BookingHistoryUiState>(BookingHistoryUiState.Idle)
    val historyUiState: StateFlow<BookingHistoryUiState> = _historyUiState

    /**
     * Create a booking
     */
    fun createBooking(request: BookingCreateRequest, userId: String? = null) {
        viewModelScope.launch {
            _uiState.value = BookingUiState.Loading
            try {
                val booking: Booking? = repository.createBooking(request)
                if (booking != null) {
                    _uiState.value = BookingUiState.Success(booking)
                    // После успешного создания обновляем историю
                    userId?.let { loadUserBookingsWithFallback(it) }
                } else {
                    _uiState.value = BookingUiState.Error("Failed to create booking (null returned)")
                }
            } catch (e: Exception) {
                _uiState.value = BookingUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = BookingUiState.Idle
    }

    fun loadUserBookingsWithFallback(userId: String) {
        viewModelScope.launch {
            _historyUiState.value = BookingHistoryUiState.Loading
            try {
                val bookings = repository.getUserBookings(userId)
                if (bookings.isNotEmpty()) {
                    _historyUiState.value = BookingHistoryUiState.Success(bookings)
                } else {
                    _historyUiState.value = BookingHistoryUiState.Success(repository.stubBookings.filter { it.user_id == userId })
                }
            } catch (e: Exception) {
                _historyUiState.value = BookingHistoryUiState.Success(repository.stubBookings.filter { it.user_id == userId })
            }
        }
    }

}
