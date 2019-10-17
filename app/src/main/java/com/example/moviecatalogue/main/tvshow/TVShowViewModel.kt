package com.example.moviecatalogue.main.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.DataDummy
import com.example.moviecatalogue.entity.FilmEntity

class TVShowViewModel : ViewModel() {

    fun getTVShows() : MutableList<FilmEntity> {
        return DataDummy().generateTVShowData()
    }

}