package com.example.pruebaandroid.view.viewmodel

import androidx.lifecycle.*
import com.example.pruebaandroid.businesslogic.database.dao.PopularMovieDao
import com.example.pruebaandroid.businesslogic.respositories.PopularMovieApiRepo
import com.example.pruebaandroid.model.PopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PopularMovieApiRepo,
    private val popularMovieDao: PopularMovieDao
) : ViewModel() {
    private var _response = MutableLiveData<List<PopularMovie>>()
    val response: LiveData<List<PopularMovie>> = popularMovieDao.getPopularMovies().asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        _isLoading.value = true
        try {
            val list = repository.getPopularMovieList().popularMovieList
            popularMovieDao.insertList(list)
        } catch (erro: Throwable) {

        }
        _isLoading.value = false
    }
}