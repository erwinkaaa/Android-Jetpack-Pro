package com.example.moviecatalogue.repository.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.repository.local.LocalRepository
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.remote.ApiResponse
import com.example.moviecatalogue.repository.remote.RemoteRepository
import com.example.moviecatalogue.repository.remote.response.movie.MovieModel
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.util.InstantAppExecutors
import com.example.moviecatalogue.util.LiveDataTestUtil
import com.example.moviecatalogue.util.PagedListUtil
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainRepositoryTest {

    private val remote = mock(RemoteRepository::class.java)
    private val local = mock(LocalRepository::class.java)
    private val appExecutors = mock(InstantAppExecutors::class.java)
    private val mainRepository = MainRepository(remote, local, appExecutors)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteMoviesPaged() {
        val movies = listOf<MovieEntity>()
        val dataSourceFactory = mock(DataSource.Factory::class.java)
        `when`(local.getFavoriteMoviesPaged()).thenReturn(dataSourceFactory as DataSource.Factory<Int, MovieEntity>)
        mainRepository.getFavoriteMoviesPaged()
        val result = Resource.success(PagedListUtil.mockPagedList(movies))
        verify(local).getFavoriteMoviesPaged()
        assertNotNull(result.data)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteTVShowsPaged() {
        val tv = listOf<TvEntity>()
        val dataSourceFactory = mock(DataSource.Factory::class.java)
        `when`(local.getFavoriteTvShowsPaged()).thenReturn(dataSourceFactory as DataSource.Factory<Int, TvEntity>)
        mainRepository.getFavoriteTVShowsPaged()
        val result = Resource.success(PagedListUtil.mockPagedList(tv))
        verify(local).getFavoriteTvShowsPaged()
        assertNotNull(result.data)
    }
}