package com.example.apod.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryItem (
    val title: String,
    val url: String
)