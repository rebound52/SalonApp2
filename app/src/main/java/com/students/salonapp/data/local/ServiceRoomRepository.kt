package com.students.salonapp.data.local

import com.students.salonapp.data.models.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ServiceRoomRepository(private val serviceDao: ServiceDao) {
    suspend fun getAllServices(categoryId: String? = null): List<Service> = withContext(Dispatchers.IO) {
        val entities = if (categoryId == null) {
            serviceDao.getAllServices()
        } else {
            serviceDao.getServicesByCategory(categoryId)
        }
        entities.map {
            Service(
                id = it.id,
                name = it.name,
                description = it.description,
                duration = it.duration,
                price = it.price,
                image_url = it.image_url,
                category_id = it.category_id
            )
        }
    }
}

