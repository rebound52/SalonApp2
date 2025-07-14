package com.students.salonapp.data.local

import com.students.salonapp.data.models.Master
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MasterRoomRepository(private val masterDao: MasterDao) {
    suspend fun getAllMasters(): List<Master> = withContext(Dispatchers.IO) {
        masterDao.getAllMasters().map {
            Master(
                id = it.id,
                name = it.name,
                rating = it.rating,
                bio = it.bio,
                image_url = it.image_url
            )
        }
    }
}

