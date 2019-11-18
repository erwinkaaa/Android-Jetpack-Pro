package com.example.moviecatalogue.repository.remote.response.movie

data class MovieModel(
    val id: String,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val overview: String,
    val vote_average: Double,
    val popularity: Double
)