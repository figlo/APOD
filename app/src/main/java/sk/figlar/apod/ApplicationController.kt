package sk.figlar.apod

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltAndroidApp
class ApplicationController : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun delayedInit() {
        applicationScope.launch {
            if (sk.figlar.apod.BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        delayedInit()
        instance = this
    }

    companion object {
        lateinit var instance: ApplicationController
            private set
    }
}