package com.example.pruebaandroid.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaandroid.businesslogic.respositories.PopularMovieApiRepo
import com.example.pruebaandroid.model.PopularMovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PopularMovieApiRepo) : ViewModel() {
    private val _response = MutableLiveData<PopularMovieResponse>()
    val response: LiveData<PopularMovieResponse> get() = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        _isLoading.value = true
        _response.value = repository.getPopularMovieList()
        _isLoading.value = false
    }
}