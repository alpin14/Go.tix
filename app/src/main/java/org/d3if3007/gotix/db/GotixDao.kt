package org.d3if3007.gotix.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GotixDao{
    @Insert
    fun insert(gotix: GotixEntity)

    @Query("SELECT * FROM gotix ORDER BY id DESC")
    fun getLastTiket(): LiveData<List<GotixEntity?>>

    @Query("DELETE FROM gotix")
    fun clearData()

    @Query("DELETE FROM gotix WHERE id = :id")
    fun deleteHistory(id: Long)
}