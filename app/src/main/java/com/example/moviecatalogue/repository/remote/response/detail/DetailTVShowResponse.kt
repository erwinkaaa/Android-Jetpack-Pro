package com.example.moviecatalogue.repository.remote.response.detail

data class DetailTVShowResponse(
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val name: String,
    val overview: String,
    val poster_path: String,
    val production_companies: List<ProductionCompanyResponse>
)