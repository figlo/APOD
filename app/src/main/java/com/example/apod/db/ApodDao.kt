package com.example.apod.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {

    @Insert
    suspend fun insert(apod: Apod)

    @Query("SELECT * FROM apods ORDER BY date DESC")
    fun getAllApods(): Flow<List<Apod>>
}