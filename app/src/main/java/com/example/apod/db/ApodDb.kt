package com.example.apod.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Apod::class],
    version = 1,
    exportSchema = true,
)

@TypeConverters(Converters::class)
abstract class ApodDb : RoomDatabase() {
    abstract val apodDao: ApodDao
}