package com.non.mornhouse.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.non.mornhouse.data.entities.Number

@Dao
interface NumberDao {
    @Query("DELETE FROM numbers")
    suspend fun deleteAll()

    @Query("SELECT * FROM numbers ORDER BY id DESC")
    fun getAllNumbers(): LiveData<List<Number>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumbers(movies: List<Number>)
}