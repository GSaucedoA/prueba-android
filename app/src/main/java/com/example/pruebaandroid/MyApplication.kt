package com.example.pruebaandroid

import android.app.Application
import androidx.work.Configuration
import com.example.pruebaandroid.businesslogic.Workers.LocationWorkerFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(LocationWorkerFactory(this)).build()
    }
}