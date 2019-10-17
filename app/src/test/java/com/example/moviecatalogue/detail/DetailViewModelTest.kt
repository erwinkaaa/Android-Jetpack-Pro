package com.example.moviecatalogue.detail

import com.example.moviecatalogue.R
import com.example.moviecatalogue.entity.FilmEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dummyDetail: FilmEntity

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
        dummyDetail = FilmEntity(
            "A Star is Born",
            "A Star Is Born is a 2018 American musical romantic drama film produced and directed by Bradley Cooper and written by Eric Roth, Cooper and Will Fetters. A remake of the 1937 film of the same name, it stars Cooper, Lady Gaga, Dave Chappelle, Andrew Dice Clay, and Sam Elliott, and follows a hard-drinking musician (Cooper) who discovers and falls in love with a young singer (Gaga). It marks the third remake of the original 1937 film, after the 1954 musical and the 1976 musical.",
            R.drawable.poster_a_start_is_born,
            "October 5, 2018",
            "148 minutes",
            "Warner Bros. Pictures")
    }

    @Test
    fun getData() {
        detailViewModel.setDataDetail(dummyDetail)
        assertEquals(detailViewModel.title, dummyDetail.title)
        assertEquals(detailViewModel.description, dummyDetail.description)
        assertEquals(detailViewModel.poster, dummyDetail.poster)
        assertEquals(detailViewModel.releaseDate, dummyDetail.releaseDate)
        assertEquals(detailViewModel.durationTime, dummyDetail.runningTime)
        assertEquals(detailViewModel.distributedBy, dummyDetail.distributedBy)
    }
}