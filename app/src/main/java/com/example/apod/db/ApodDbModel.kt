package com.example.apod.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apod.ApodDomainModel
import com.example.apod.api.ApodApiModel

@Entity(tableName = "apods")
data class ApodDbModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

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

fun List<ApodDbModel>.toApiModel(): List<ApodApiModel> {
    return map {
        ApodApiModel(
            title = it.title,
            date = it.date,
            explanation = it.explanation,
            url = it.url,
            mediaType = it.mediaType,
        )
    }
}

fun List<ApodDbModel>.toDomainModel(): List<ApodDomainModel> {
    return map {
        ApodDomainModel(
            id = it.id,
            title = it.title,
            date = it.date,
            explanation = it.explanation,
            url = it.url,
            mediaType = it.mediaType,
        )
    }
}

fun ApodDbModel.toDomainModel(): ApodDomainModel {
    return ApodDomainModel(
        id = this.id,
        title = this.title,
        date = this.date,
        explanation = this.explanation,
        url = this.url,
        mediaType = this.mediaType,
    )
}