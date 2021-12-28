package com.example.pruebaandroid.model

import com.google.gson.annotations.SerializedName

data class PoPularMovieResponse(@SerializedName("results") val popularMovieList: List<PopularMovie>)
