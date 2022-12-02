package sk.figlar.apod.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apods: List<ApodDbModel>)

    @Query("SELECT * FROM apods ORDER BY date DESC")
    fun getApodDbModelsFlow(): Flow<List<ApodDbModel>>

    @Query("SELECT * FROM apods WHERE id=:id")
    suspend fun getApodDbModel(id: Long): ApodDbModel?
}