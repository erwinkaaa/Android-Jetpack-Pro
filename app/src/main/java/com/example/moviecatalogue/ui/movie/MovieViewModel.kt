package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository


class MovieViewModel(mainRepository: MainRepository) : ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val movies = Transformations.switchMap(bait) { mainRepository.getMovies() }

}