package com.example.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.util.LiveDataTestUtil
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java) as Observer<*>

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
        val dummy = Resource.success(listOf<MovieEntity>())
        val dataMovies = MutableLiveData<Resource<List<MovieEntity>>>()
        dataMovies.value = dummy

        `when`(mainRepository.getMovies()).thenReturn(dataMovies)

        movieViewModel.insertBait()
        movieViewModel.movies.observeForever(observer as Observer<Resource<List<MovieEntity>>>)

        val result = LiveDataTestUtil.getValue(movieViewModel.movies)

        verify(observer).onChanged(dummy)
        assertNotNull(result.data)
    }
}