package com.example.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository


class FavoriteMovieViewModel(mainRepository: MainRepository) : ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val favoriteMovies = Transformations.switchMap(bait) { mainRepository.getFavoriteMoviesPaged() }

}