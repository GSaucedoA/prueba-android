package com.example.pruebaandroid.businesslogic.respositories


import com.example.pruebaandroid.BuildConfig
import com.example.pruebaandroid.businesslogic.api.MovieDataBaseApiService
import com.example.pruebaandroid.model.PopularMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMovieApiRepoImpl @Inject constructor(private val apiService: MovieDataBaseApiService) :
    PopularMovieApiRepo {
    override suspend fun getPopularMovieList(): PopularMovieResponse =
        withContext(Dispatchers.IO) {
            apiService.getPopularMovieList(BuildConfig.API_KEY)
        }
}