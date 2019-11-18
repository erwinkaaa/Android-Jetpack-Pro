package com.example.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository

class FavoriteTVShowViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val favoriteTv = Transformations.switchMap(bait) { mainRepository.getFavoriteTVShowsPaged() }
}