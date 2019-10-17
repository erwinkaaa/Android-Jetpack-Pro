package com.example.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.entity.FilmEntity

class DetailViewModel : ViewModel() {
    var title = ""
    var poster = 0
    var releaseDate = ""
    var durationTime = ""
    var distributedBy = ""
    var description = ""

    fun setDataDetail(film: FilmEntity) {
        title = film.title
        poster = film.poster
        releaseDate = film.releaseDate
        durationTime = film.runningTime
        distributedBy = film.distributedBy
        description = film.description
    }
}