package com.example.apod.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ApodDbModel::class],
    version = 1,
    exportSchema = true,
)
abstract class ApodDb : RoomDatabase() {
    abstract val apodDao: ApodDao
}