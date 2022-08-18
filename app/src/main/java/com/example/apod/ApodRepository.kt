package com.example.apod

import com.example.apod.api.ApodApi
import com.example.apod.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ApodRepository {
    private val apodApi: ApodApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        apodApi = retrofit.create()
    }

    suspend fun fetchApods(): List<GalleryItem> = apodApi.fetchApods()
}