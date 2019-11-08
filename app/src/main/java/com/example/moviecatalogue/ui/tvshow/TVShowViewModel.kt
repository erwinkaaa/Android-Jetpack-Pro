package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity

class TVShowViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getLiveDataMovies(): LiveData<List<TVShowEntity>> {
        return mainRepository.getTVShows()
    }

}