package sk.figlar.apod

import sk.figlar.apod.api.ApodApi
import sk.figlar.apod.api.toDbModel
import sk.figlar.apod.db.ApodDao
import sk.figlar.apod.db.ApodDbModel
import sk.figlar.apod.db.toApiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber
import javax.inject.Inject

class ApodRepository @Inject constructor(private val dao: ApodDao) {
    private val apodApi: ApodApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        apodApi = retrofit.create()
    }

    fun getDbApodsFlow(): Flow<List<ApodDbModel>> = dao.getDbApodsFlow()

    suspend fun getApod(apodId: Long): ApodDbModel? = dao.getDbApod(apodId)

    suspend fun refreshApods() {
        try {
            val apiApods = apodApi.getApiApods()
            val dbApods = dao.getDbApodsFlow().first()
            val newApiApods = apiApods - dbApods.toApiModel().toSet()
            dao.insertAll(newApiApods.toDbModel())
        } catch (ex: Exception) {
            Timber.e("Failed to fetch gallery items", ex)
        }
    }
}