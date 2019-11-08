package com.example.moviecatalogue.repository.remote.callback

import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import com.example.moviecatalogue.repository.remote.response.movie.MovieResponse
import retrofit2.Call

interface CallbackGetMovies {
    fun onSuccess(data: List<MovieEntity>)
    fun onFailed(call: Call<MovieResponse>, error: Throwable)
}