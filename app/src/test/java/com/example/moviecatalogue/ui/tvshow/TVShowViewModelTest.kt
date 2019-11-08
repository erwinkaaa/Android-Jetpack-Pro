package com.example.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TVShowViewModelTest {

    private lateinit var tvShowViewModel: TVShowViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tvShowViewModel = TVShowViewModel(mainRepository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getTVShows() {
        val dummy = listOf<TVShowEntity>()
        val dataTVShows = MutableLiveData<List<TVShowEntity>>()
        dataTVShows.value = dummy

        `when`(mainRepository.getTVShows()).thenReturn(dataTVShows)
        tvShowViewModel.getLiveDataMovies().observeForever(observer as Observer<List<TVShowEntity>>)

        verify(observer).onChanged(dummy)
        verify(mainRepository).getTVShows()
        verify(mainRepository).liveTVShows
    }
}