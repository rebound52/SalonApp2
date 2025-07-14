package com.students.salonapp

import android.app.Application
import androidx.room.Room
import com.students.salonapp.data.local.*

class App : Application() {
    companion object {
        var instance: App? = null
            private set
    }

    lateinit var database: AppDatabase
        private set
    lateinit var bookingRoomRepo: BookingRoomRepository
        private set
    lateinit var categoryRoomRepo: CategoryRoomRepository
        private set
    lateinit var serviceRoomRepo: ServiceRoomRepository
        private set
    lateinit var masterRoomRepo: MasterRoomRepository
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "salonapp-db"
        ).build()
        bookingRoomRepo = BookingRoomRepository(database.bookingDao())
        categoryRoomRepo = CategoryRoomRepository(database.categoryDao())
        serviceRoomRepo = ServiceRoomRepository(database.serviceDao())
        masterRoomRepo = MasterRoomRepository(database.masterDao())
    }
}

