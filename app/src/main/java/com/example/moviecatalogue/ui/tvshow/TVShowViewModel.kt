package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository

class TVShowViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "This is bait"
    }

    val tv = Transformations.switchMap(bait) { mainRepository.getTVShows() }

}