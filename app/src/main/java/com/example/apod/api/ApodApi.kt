package com.example.apod.api

import retrofit2.http.GET

interface ApodApi {
    @GET("/")
    suspend fun fetchContents(): String
}