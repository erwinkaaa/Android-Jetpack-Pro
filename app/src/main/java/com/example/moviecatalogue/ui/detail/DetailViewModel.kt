package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.main.MainRepository

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var detailMovie = MutableLiveData<MovieEntity>()
    var detailTv = MutableLiveData<TvEntity>()

    fun setMovieFavoriteState(movieEntity: MovieEntity, state: Boolean) {
        mainRepository.setMovieFavoriteState(movieEntity, state)
    }

    fun setTvFavoriteState(tvEntity: TvEntity, state: Boolean) {
        mainRepository.setTvFavoriteState(tvEntity, state)
    }

}