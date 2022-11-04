package com.example.apod.api

import com.example.apod.db.ApodDbModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApodApiModel(
    val title: String,
    val date: String,
    val copyright: String?,
    val explanation: String,
    val url: String,
    @Json(name = "media_type") val mediaType: String,
)

fun List<ApodApiModel>.toDbModel(): List<ApodDbModel> {
    return map {
        ApodDbModel(
            title = it.title,
            date = it.date,
            copyright = it.copyright ?: "",
            explanation = it.explanation,
            url = it.url,
            mediaType = it.mediaType,
        )
    }
}