package com.example.moviecatalogue.repository.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.repository.remote.RemoteRepository
import com.example.moviecatalogue.repository.remote.callback.CallbackGetDetailMovie
import com.example.moviecatalogue.repository.remote.callback.CallbackGetDetailTv
import com.example.moviecatalogue.repository.remote.callback.CallbackGetMovies
import com.example.moviecatalogue.repository.remote.callback.CallbackGetTVShows
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import com.example.moviecatalogue.util.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainRepositoryTest {

    private val remote = mock(RemoteRepository::class.java)
    private val mainRepository = MainRepository(remote)
    private val dataMovies = listOf<MovieEntity>()
    private val dataTv = listOf<TVShowEntity>()
    private val detailMovie = DetailMovieResponse("", "", "", mutableListOf(), "", "")
    private val detailTv = DetailTVShowResponse(mutableListOf(), "", "", "", "", mutableListOf())
    private val idMovie = "475557"
    private val idTv = "62286"

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

    @Test
    fun getMoviesFromAPI() {

        doAnswer {
            (it.arguments[0] as CallbackGetMovies).onSuccess(dataMovies)
            null
        }.`when`(remote).getMoviesFromAPI(this.any())

        val result = LiveDataTestUtil.getValue(mainRepository.getMovies())

        verify(remote, times(1)).getMoviesFromAPI(this.any())

        assertNotNull(result)
    }

    @Test
    fun getTVShowsFromAPI() {

        doAnswer {
            (it.arguments[0] as CallbackGetTVShows).onSuccess(dataTv)
            null
        }.`when`(remote).getTVShowsFromAPI(this.any())

        val result = LiveDataTestUtil.getValue(mainRepository.getTVShows())

        verify(remote, times(1)).getTVShowsFromAPI(this.any())

        assertNotNull(result)
    }

    @Test
    fun getMovieDetailFromAPI() {

        doAnswer {
            (it.arguments[1] as CallbackGetDetailMovie).onSuccess(detailMovie)
            null
        }.`when`(remote).getMovieDetailFromAPI(eq(idMovie), this.any())

        val result = LiveDataTestUtil.getValue(mainRepository.getDetailMovie(idMovie))

        verify(remote, times(1)).getMovieDetailFromAPI(eq(idMovie), this.any())

        assertNotNull(result)
    }

    @Test
    fun getTvDetailFromAPI() {

        doAnswer {
            (it.arguments[1] as CallbackGetDetailTv).onSuccess(detailTv)
            null
        }.`when`(remote).getTvShowDetailFromAPI(eq(idTv), this.any())

        val result = LiveDataTestUtil.getValue(mainRepository.getDetailTvShow(idTv))

        verify(remote, times(1)).getTvShowDetailFromAPI(eq(idTv), this.any())

        assertNotNull(result)
    }
}