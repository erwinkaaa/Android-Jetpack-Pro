package com.example.moviecatalogue.main.movie

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val data = movieViewModel.getMovies()
        assertNotNull(data)
        assertEquals(10, data.size)
    }
}