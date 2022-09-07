package com.example.apod.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apods")
data class Apod(
    @PrimaryKey(autoGenerate = true)
    val apodId: Long = 0L,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "date")
    val date: String = "",

    @ColumnInfo(name = "explanation")
    val explanation: String = "",

    @ColumnInfo(name = "url")
    val url: String = "",

    @ColumnInfo(name = "media_type")
    val mediaType: String = "",
)