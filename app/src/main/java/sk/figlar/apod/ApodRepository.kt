package sk.figlar.apod

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import sk.figlar.apod.api.ApodApi
import sk.figlar.apod.api.toDbModel
import sk.figlar.apod.db.ApodDao
import sk.figlar.apod.db.ApodDbModel
import sk.figlar.apod.db.toApiModel
import sk.figlar.apod.db.toDomainModel
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApodRepository @Inject constructor(
    private val dao: ApodDao,
    private val apodApi: ApodApi,
) {
    fun getApodDbModelsFlow(): Flow<List<ApodDbModel>> = dao.getApodDbModelsFlow()

    suspend fun getApod(apodId: Long): ApodDomainModel? = dao.getApodDbModel(apodId)?.toDomainModel()

    suspend fun refreshApods() {
        try {
            val apiApods = apodApi.getApodApiModels()
            val dbApods = dao.getApodDbModelsFlow().first()
            val newApiApods = apiApods - dbApods.toApiModel().toSet()
            dao.insertAll(newApiApods.toDbModel())
        } catch (ex: Exception) {
            Timber.e("Failed to fetch gallery items", ex)
        }
    }
}