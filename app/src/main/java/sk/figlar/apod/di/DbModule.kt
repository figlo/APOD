package sk.figlar.apod.di

import android.content.Context
import androidx.room.Room
import sk.figlar.apod.db.ApodDao
import sk.figlar.apod.db.ApodDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "apod_db"

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Singleton
    @Provides
    fun provideApodDb(@ApplicationContext appContext: Context): ApodDb =
        Room.databaseBuilder(
            appContext,
            ApodDb::class.java,
            DB_NAME,
        ).build()

    @Singleton
    @Provides
    fun provideApodDao(db: ApodDb): ApodDao = db.apodDao
}