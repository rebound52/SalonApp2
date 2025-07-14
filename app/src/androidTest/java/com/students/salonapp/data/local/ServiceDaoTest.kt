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
class ServiceDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var serviceDao: ServiceDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        serviceDao = db.serviceDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetAllServices() = runBlocking {
        val service = ServiceEntity(
            id = "s1",
            name = "Маникюр",
            description = "Классический маникюр",
            duration = 60,
            price = 1200.0,
            image_url = null,
            category_id = "cat1"
        )
        serviceDao.insertService(service)
        val services = serviceDao.getAllServices()
        assertEquals(1, services.size)
        assertEquals("Маникюр", services[0].name)
    }

    @Test
    fun insertAndGetServicesByCategory() = runBlocking {
        val service1 = ServiceEntity("s1", "Маникюр", "desc", 60, 1200.0, null, "cat1")
        val service2 = ServiceEntity("s2", "Педикюр", "desc", 60, 1300.0, null, "cat2")
        serviceDao.insertService(service1)
        serviceDao.insertService(service2)
        val cat1Services = serviceDao.getServicesByCategory("cat1")
        assertEquals(1, cat1Services.size)
        assertEquals("Маникюр", cat1Services[0].name)
    }
}

