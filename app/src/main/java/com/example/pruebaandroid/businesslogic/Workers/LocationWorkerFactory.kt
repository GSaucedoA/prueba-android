package com.example.pruebaandroid.businesslogic.Workers

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.pruebaandroid.MyApplication
import com.example.pruebaandroid.businesslogic.managers.LocationManager
import com.example.pruebaandroid.businesslogic.respositories.LocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LocationWorkerFactory(private val application: MyApplication) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        Log.e("WorkerFactoy", "entra")
        return when (workerClassName) {
            LocationWorker::class.java.canonicalName -> LocationWorker(
                appContext,
                workerParameters,
                LocationRepository(
                    appContext,
                    LocationManager(appContext),
                    CoroutineScope(Dispatchers.IO)
                )
            )
            else -> null
        }
    }
}