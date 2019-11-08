package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity

class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getLiveDataMovies(): LiveData<List<MovieEntity>> {
        return mainRepository.getMovies()
    }

}