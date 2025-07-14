package com.students.salonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookingEntity::class, CategoryEntity::class, ServiceEntity::class, MasterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
    abstract fun categoryDao(): CategoryDao
    abstract fun serviceDao(): ServiceDao
    abstract fun masterDao(): MasterDao
}
