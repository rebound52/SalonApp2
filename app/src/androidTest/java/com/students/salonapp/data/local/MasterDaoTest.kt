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
class MasterDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var masterDao: MasterDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        masterDao = db.masterDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetAllMasters() = runBlocking {
        val master = MasterEntity(id = "m1", name = "Иван", rating = 4.9, bio = "Парикмахер", image_url = null)
        masterDao.insertMaster(master)
        val masters = masterDao.getAllMasters()
        assertEquals(1, masters.size)
        assertEquals("Иван", masters[0].name)
    }
}

