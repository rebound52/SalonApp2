package com.students.salonapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookingDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var bookingDao: BookingDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        bookingDao = db.bookingDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetBookingByUser() = runBlocking {
        val booking = BookingEntity(
            service_id = "service1",
            master_id = "master1",
            user_id = "user1",
            date = "2025-06-20",
            time = "10:00",
            total_price = 100.0
        )
        bookingDao.insertBooking(booking)
        val bookings = bookingDao.getBookingsByUser("user1")
        assertEquals(1, bookings.size)
        assertEquals("service1", bookings[0].service_id)
    }
}
