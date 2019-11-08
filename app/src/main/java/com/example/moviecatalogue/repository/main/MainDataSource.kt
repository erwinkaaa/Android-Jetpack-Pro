package com.example.moviecatalogue.repository.main

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse

interface MainDataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getTVShows(): LiveData<List<TVShowEntity>>
    fun getDetailMovie(id: String): LiveData<DetailMovieResponse>
    fun getDetailTvShow(id: String): LiveData<DetailTVShowResponse>
}