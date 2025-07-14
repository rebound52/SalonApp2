package com.students.salonapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaster(master: MasterEntity): Long

    @Query("SELECT * FROM masters ORDER BY name ASC")
    suspend fun getAllMasters(): List<MasterEntity>
}

