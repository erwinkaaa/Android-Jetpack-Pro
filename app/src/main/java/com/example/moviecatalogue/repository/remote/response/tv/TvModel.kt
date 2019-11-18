package com.example.moviecatalogue.repository.remote.response.tv

data class TvModel(
    val id: String,
    val name: String,
    val poster_path: String,
    val first_air_date: String,
    val overview: String,
    val vote_average: Double,
    val popularity: Double
)