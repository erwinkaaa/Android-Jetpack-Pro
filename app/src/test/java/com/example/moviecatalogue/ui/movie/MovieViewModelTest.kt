package com.example.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieViewModel = MovieViewModel(mainRepository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getMovies() {
        val dummy = listOf<MovieEntity>()
        val dataMovies = MutableLiveData<List<MovieEntity>>()
        dataMovies.value = dummy

        `when`(mainRepository.getMovies()).thenReturn(dataMovies)
        movieViewModel.getLiveDataMovies().observeForever(observer as Observer<List<MovieEntity>>)

        verify(observer).onChanged(dummy)
        verify(mainRepository).getMovies()
        verify(mainRepository).liveMovies
    }
}