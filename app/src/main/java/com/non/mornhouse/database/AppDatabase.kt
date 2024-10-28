package com.non.mornhouse.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.non.mornhouse.data.entities.Number
import com.non.mornhouse.dao.NumberDao

@Database(entities = [Number::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDao
}