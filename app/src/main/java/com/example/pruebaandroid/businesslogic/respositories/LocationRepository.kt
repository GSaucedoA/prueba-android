package com.example.pruebaandroid.businesslogic.respositories

import android.content.Context
import android.location.Geocoder
import com.example.pruebaandroid.R
import com.example.pruebaandroid.businesslogic.Workers.makeStatusNotification
import com.example.pruebaandroid.businesslogic.managers.LocationManager
import com.example.pruebaandroid.model.Location
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class LocationRepository(
    private val context: Context,
    private val locationManager: LocationManager,
    private val scope: CoroutineScope
) {
    fun getLocation() {
        locationManager.getUpdatedLocation { location -> onLocationReceived(location) }
    }

    private fun onLocationReceived(location: android.location.Location?) = scope.launch {
        location?.let { newLocation ->
            val date = Calendar.getInstance().timeInMillis
            val cityName: String =
                Geocoder(context).getFromLocation(
                    newLocation.latitude,
                    newLocation.longitude,
                    1
                )[0].locality
            saveLocation(
                Location(
                    date = date,
                    cityName = cityName,
                    latitude = newLocation.latitude,
                    longitude = newLocation.longitude
                )
            )
        }
    }

    private fun saveLocation(location: Location) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        db.collection("Location").document(location.date.toString())
            .set(location)
            .addOnSuccessListener {
                makeStatusNotification(context.getString(R.string.success_location_send), context)
            }
    }
}