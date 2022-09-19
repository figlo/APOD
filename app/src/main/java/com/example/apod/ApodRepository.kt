package com.example.apod

import com.example.apod.api.ApodApi
import com.example.apod.api.toDbModel
import com.example.apod.db.ApodDao
import com.example.apod.db.ApodDbModel
import com.example.apod.db.toApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

class ApodRepository(private val dao: ApodDao) {
    private val apodApi: ApodApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        apodApi = retrofit.create()
    }

    fun getDbApodsFlow(): Flow<List<ApodDbModel>> = dao.getDbApodsFlow()

    suspend fun refreshApods() {
        try {
            withContext(Dispatchers.IO) {
                val apiApods = async { apodApi.getApiApods() }
                val dbApods = async { dao.getDbApodsFlow().first() }
                val newApiApods = apiApods.await() - dbApods.await().toApiModel().toSet()
                dao.insertAll(newApiApods.toDbModel())
            }
        } catch (ex: Exception) {
            Timber.e("Failed to fetch gallery items", ex)
        }
    }
}