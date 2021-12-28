package com.example.pruebaandroid.businesslogic.respositories

import com.example.pruebaandroid.BuildConfig
import com.example.pruebaandroid.model.PoPularMovieResponse

interface PopularMovieApiRepo {
    suspend fun getPopularMovieList(): PoPularMovieResponse
}