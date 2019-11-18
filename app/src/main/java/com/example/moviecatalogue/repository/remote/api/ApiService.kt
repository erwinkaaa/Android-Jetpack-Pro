package com.example.moviecatalogue.repository.remote.api

import com.example.moviecatalogue.repository.remote.response.movie.MovieResponse
import com.example.moviecatalogue.repository.remote.response.tv.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun loadMovies(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>

    @GET("discover/tv")
    fun loadTVShows(
        @Query("api_key") api_key: String
    ): Call<TVShowResponse>

}