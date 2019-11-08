package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("UNCHECKED_CAST")
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val idMovie = "475557"
    private val idTv = "62286"
    private lateinit var dummyMovie: DetailMovieResponse
    private lateinit var dummyTv: DetailTVShowResponse

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailViewModel = DetailViewModel(mainRepository)

        dummyMovie = DetailMovieResponse(
            "dummy",
            "dummy",
            "dummy",
            mutableListOf(),
            "dummy",
            "dummy"
        )

        dummyTv = DetailTVShowResponse(
            mutableListOf(),
            "dummy",
            "dummy",
            "dummy",
            "dummy",
            mutableListOf()
        )
    }

    @Test
    fun getDetailMovie() {
        val dataMovie = MutableLiveData<DetailMovieResponse>()
        dataMovie.value = dummyMovie

        val observer = mock(Observer::class.java)
        `when`(mainRepository.getDetailMovie(idMovie)).thenReturn(dataMovie)
        detailViewModel.getLiveDataDetailMovie(idMovie)
            .observeForever(observer as Observer<DetailMovieResponse>)

        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTv() {
        val dataMovie = MutableLiveData<DetailTVShowResponse>()
        dataMovie.value = dummyTv

        val observer = mock(Observer::class.java)
        `when`(mainRepository.getDetailTvShow(idTv)).thenReturn(dataMovie)
        detailViewModel.getLiveDataDetailTVShow(idTv)
            .observeForever(observer as Observer<DetailTVShowResponse>)

        verify(observer).onChanged(dummyTv)
    }

}