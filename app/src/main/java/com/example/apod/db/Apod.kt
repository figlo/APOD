package com.example.apod.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apods")
data class Apod(
    @PrimaryKey(autoGenerate = true)
    var apodId: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "explanation")
    var explanation: String = "",

    @ColumnInfo(name = "url")
    var url: String = "",
)