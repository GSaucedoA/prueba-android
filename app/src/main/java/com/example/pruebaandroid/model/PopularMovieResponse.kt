package com.example.pruebaandroid.model

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(@SerializedName("results") val popularMovieList: List<PopularMovie>)
