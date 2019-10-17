package com.example.moviecatalogue.main.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.DataDummy
import com.example.moviecatalogue.entity.FilmEntity

class MovieViewModel : ViewModel() {

    fun getMovies() : MutableList<FilmEntity> {
        return DataDummy().generateMovieData()
    }

}