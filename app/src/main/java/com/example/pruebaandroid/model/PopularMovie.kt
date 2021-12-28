package com.example.pruebaandroid.model

import com.google.gson.annotations.SerializedName

data class PopularMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String
)
