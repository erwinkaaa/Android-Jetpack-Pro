package com.example.moviecatalogue.repository.remote.response.detail

data class DetailMovieResponse(
    val title: String,
    val poster_path: String,
    val release_date: String,
    val production_companies: List<ProductionCompanyResponse>,
    val overview: String,
    val runtime: String
)