package com.example.pruebaandroid.businesslogic.managers

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

class LocationManager(val context: Context) {
    @SuppressLint("MissingPermission")
    fun getUpdatedLocation(block: (Location?) -> Unit) {
        val fineLocationpermission =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationpermission =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fineLocationpermission == PackageManager.PERMISSION_GRANTED && coarseLocationpermission == PackageManager.PERMISSION_GRANTED) {
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { location ->
                block.invoke(location)
            }
        } else block.invoke(null)
    }
}