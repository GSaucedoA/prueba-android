package com.example.pruebaandroid.businesslogic.respositories

import com.example.pruebaandroid.model.PopularMovieResponse

interface PopularMovieApiRepo {
    suspend fun getPopularMovieList(): PopularMovieResponse
}