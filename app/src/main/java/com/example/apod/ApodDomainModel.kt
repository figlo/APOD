package com.example.apod

data class ApodDomainModel(
    val title: String,
    val date: String,
    val explanation: String,
    val url: String,
    val mediaType: String,
)
