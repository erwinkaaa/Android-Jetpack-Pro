package com.example.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.util.LiveDataTestUtil
import junit.framework.Assert.assertNotNull
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
        val dummy = Resource.success(listOf<TvEntity>())
        val dataTv = MutableLiveData<Resource<List<TvEntity>>>()
        dataTv.value = dummy

        `when`(mainRepository.getTVShows()).thenReturn(dataTv)

        tvShowViewModel.insertBait()
        tvShowViewModel.tv.observeForever(observer as Observer<Resource<List<TvEntity>>>)

        val result = LiveDataTestUtil.getValue(tvShowViewModel.tv)

        verify(observer).onChanged(dummy)
        assertNotNull(result.data)
    }
}