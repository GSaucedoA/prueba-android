package com.example.pruebaandroid.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pruebaandroid.businesslogic.database.dao.PopularMovieDao
import com.example.pruebaandroid.businesslogic.respositories.PopularMovieApiRepo
import com.example.pruebaandroid.model.PopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDatabaseViewModel @Inject constructor(
    private val repository: PopularMovieApiRepo,
    private val popularMovieDao: PopularMovieDao
) : ViewModel() {
    val response: LiveData<List<PopularMovie>> = popularMovieDao.getPopularMovies().asLiveData()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        try {
            val list = repository.getPopularMovieList().popularMovieList
            popularMovieDao.insertList(list)
        } catch (erro: Throwable) {
        }
    }
}