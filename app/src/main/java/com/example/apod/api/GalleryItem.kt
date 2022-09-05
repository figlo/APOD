package com.example.apod.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryItem (
    val title: String,
    val date: String,
    val explanation: String,
    val url: String,
    @Json(name = "media_type") val mediaType: String,
)