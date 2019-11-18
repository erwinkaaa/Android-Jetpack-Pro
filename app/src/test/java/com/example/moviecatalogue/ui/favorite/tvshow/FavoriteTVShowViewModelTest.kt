package com.example.moviecatalogue.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.util.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class FavoriteTVShowViewModelTest {
    private lateinit var favoriteTVShowViewModel: FavoriteTVShowViewModel
    private val mainRepository = mock(MainRepository::class.java)
    private val observer = mock(Observer::class.java) as Observer<*>

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteTVShowViewModel = FavoriteTVShowViewModel(mainRepository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getPagedTVShows() {
        val dummy = MutableLiveData<Resource<PagedList<TvEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<TvEntity>
        dummy.value = Resource.success(pagedList)

        `when`(mainRepository.getFavoriteTVShowsPaged()).thenReturn(dummy)

        favoriteTVShowViewModel.insertBait()
        favoriteTVShowViewModel.favoriteTv.observeForever(observer as Observer<Resource<PagedList<TvEntity>>>)

        val result = LiveDataTestUtil.getValue(favoriteTVShowViewModel.favoriteTv)

        verify(observer).onChanged(Resource.success(pagedList))
        assertNotNull(result.data)
    }
}