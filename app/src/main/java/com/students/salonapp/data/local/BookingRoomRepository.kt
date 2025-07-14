package com.students.salonapp.data.local

import com.students.salonapp.data.models.Booking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookingRoomRepository(private val bookingDao: BookingDao) {

    suspend fun getUserBookings(userId: String): List<Booking> = withContext(Dispatchers.IO) {
        bookingDao.getBookingsByUser(userId).map {
            Booking(
                id = it.id.toString(),
                service_id = it.service_id,
                master_id = it.master_id,
                user_id = it.user_id,
                date = it.date,
                time = it.time,
                total_price = it.total_price
            )
        }
    }
}
