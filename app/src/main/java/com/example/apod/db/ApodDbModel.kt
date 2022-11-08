package com.example.apod.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.apod.ApodDomainModel
import com.example.apod.api.ApodApiModel

@Entity(
    tableName = "apods",
    indices = [Index(value = ["date"], unique = true)],
)
data class ApodDbModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "date")
    val date: String = "",

    @ColumnInfo(name = "copyright", defaultValue = "")
    val copyright: String = "",

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
            copyright = if (it.copyright == "") null else it.copyright,
            explanation = it.explanation,
            url = it.url,
            mediaType = it.mediaType,
        )
    }
}

fun ApodDbModel.toDomainModel(): ApodDomainModel {
    return ApodDomainModel(
        id = id,
        title = title,
        date = date,
        copyright = copyright,
        explanation = explanation,
        url = url,
        mediaType = mediaType,
    )
}