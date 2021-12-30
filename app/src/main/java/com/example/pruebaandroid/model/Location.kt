package com.example.pruebaandroid.model

import com.google.gson.annotations.SerializedName

data class Location(
    val date: Long = 0,
    val cityName: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
