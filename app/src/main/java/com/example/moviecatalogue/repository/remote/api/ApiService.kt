package com.example.moviecatalogue.repository.remote.api

import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import com.example.moviecatalogue.repository.remote.response.movie.MovieResponse
import com.example.moviecatalogue.repository.remote.response.tv.TVShowResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("discover/movie")
    fun loadMovies(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>

    @GET("discover/tv")
    fun loadTVShows(
        @Query("api_key") api_key: String
    ): Call<TVShowResponse>

    @GET("movie/{id}")
    fun loadMovieDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun loadTVShowDetail(
        @Path("id") id: String,
        @Query("api_key") api_key: String
    ): Call<DetailTVShowResponse>

}