package com.example.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.util.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class FavoriteMovieViewModelTest {
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java) as Observer<*>

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteMovieViewModel = FavoriteMovieViewModel(mainRepository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getPagedMovies() {
        val dummy = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<MovieEntity>
        dummy.value = Resource.success(pagedList)

        `when`(mainRepository.getFavoriteMoviesPaged()).thenReturn(dummy)

        favoriteMovieViewModel.insertBait()
        favoriteMovieViewModel.favoriteMovies.observeForever(observer as Observer<Resource<PagedList<MovieEntity>>>)

        val result = LiveDataTestUtil.getValue(favoriteMovieViewModel.favoriteMovies)

        verify(observer).onChanged(Resource.success(pagedList))
        assertNotNull(result.data)
    }
}