package com.example.pruebaandroid.businesslogic.Workers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pruebaandroid.businesslogic.respositories.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationWorker(
    context: Context,
    params: WorkerParameters,
    private val repository: LocationRepository
) : CoroutineWorker(context, params) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                repository.getLocation()
                Result.success()
            } catch (e: Throwable) {
                Log.e("error", e.message.toString())
                Result.failure()
            }
        }
    }

}