package com.emine.techopedia.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emine.techopedia.model.Tech

@Dao
interface TechDAO {
    @Insert
    suspend fun insertAll(vararg tech: Tech) : List<Long>
    @Query("SELECT * FROM tech")
    suspend fun getAllTech(): List<Tech>
    @Query("SELECT *FROM tech WHERE uuid=:techId")
    suspend fun getTech(techId: Int): Tech
    @Query("DELETE FROM tech")
    suspend fun deleteAllTech()
}