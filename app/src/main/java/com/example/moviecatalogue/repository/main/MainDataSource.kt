package com.example.moviecatalogue.repository.main

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.vo.Resource

interface MainDataSource {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getTVShows(): LiveData<Resource<List<TvEntity>>>
    fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getFavoriteTVShowsPaged(): LiveData<Resource<PagedList<TvEntity>>>
    fun setMovieFavoriteState(movieEntity: MovieEntity, state: Boolean)
    fun setTvFavoriteState(tvEntity: TvEntity, state: Boolean)
}