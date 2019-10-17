package com.example.moviecatalogue.main.tvshow

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {
    private lateinit var tvShowViewModel: TVShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TVShowViewModel()
    }

    @Test
    fun getTVShow() {
        val data = tvShowViewModel.getTVShows()
        assertNotNull(data)
        assertEquals(10, data.size)
    }
}