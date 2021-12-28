package com.example.pruebaandroid.businesslogic.api

import com.example.pruebaandroid.model.PoPularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataBaseApiService {
    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query("api_key") apiKey: String
    ): PoPularMovieResponse
}