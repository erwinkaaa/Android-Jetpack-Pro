package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getLiveDataDetailMovie(id: String): LiveData<DetailMovieResponse> {
        return mainRepository.getDetailMovie(id)
    }

    fun getLiveDataDetailTVShow(id: String): LiveData<DetailTVShowResponse> {
        return mainRepository.getDetailTvShow(id)
    }
}