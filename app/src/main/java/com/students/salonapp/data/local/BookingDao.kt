package com.students.salonapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: BookingEntity): Long

    @Query("SELECT * FROM bookings WHERE user_id = :userId ORDER BY date DESC")
    suspend fun getBookingsByUser(userId: String): List<BookingEntity>

    @Query("SELECT * FROM bookings ORDER BY date DESC")
    suspend fun getAllBookings(): List<BookingEntity>
}
