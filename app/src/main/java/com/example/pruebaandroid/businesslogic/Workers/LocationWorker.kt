package com.example.pruebaandroid.businesslogic.Workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.pruebaandroid.R
import com.example.pruebaandroid.model.Location
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class LocationWorker(contex: Context, params: WorkerParameters) : Worker(contex, params) {
    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        val context = applicationContext

        val client = LocationServices.getFusedLocationProviderClient(context)
        client.lastLocation.addOnSuccessListener {
            upladLocationToFirebase(
                Location(latitude = it.latitude, longitude = it.longitude),
                context
            )
        }

        return Result.success()
    }

    private fun upladLocationToFirebase(location: Location, context: Context) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        db.collection("Location").document(Calendar.getInstance().timeInMillis.toString())
            .set(location)
            .addOnSuccessListener {
                makeStatusNotification(context.getString(R.string.success_location_send), context)
            }

        /* db.collection("user").get().addOnCompleteListener {
             if (it.isSuccessful) {
                 for (document in it.result) {
                     val movie = document.toObject(PopularMovie::class.java)
                     Log.e("database:get", " => $movie")
                 }
             } else {
                 Log.e("database:error", it.exception?.message.toString())
             }
         }*/
    }

}