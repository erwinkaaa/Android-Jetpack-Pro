package com.example.moviecatalogue.repository.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.repository.remote.*
import com.example.moviecatalogue.repository.remote.callback.CallbackGetDetailMovie
import com.example.moviecatalogue.repository.remote.callback.CallbackGetDetailTv
import com.example.moviecatalogue.repository.remote.callback.CallbackGetMovies
import com.example.moviecatalogue.repository.remote.callback.CallbackGetTVShows
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import com.example.moviecatalogue.repository.remote.response.movie.MovieResponse
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import com.example.moviecatalogue.repository.remote.response.tv.TVShowResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import com.example.moviecatalogue.util.TAG
import retrofit2.Call


open class MainRepository(private val remoteRepository: RemoteRepository) : MainDataSource {

    val liveMovies = MutableLiveData<List<MovieEntity>>()
    val liveTVShows = MutableLiveData<List<TVShowEntity>>()
    val liveDetailMovie = MutableLiveData<DetailMovieResponse>()
    val liveDetailTv = MutableLiveData<DetailTVShowResponse>()

    override fun getMovies(): LiveData<List<MovieEntity>> {
        remoteRepository.getMoviesFromAPI(object :
            CallbackGetMovies {
            override fun onSuccess(data: List<MovieEntity>) {
                liveMovies.postValue(data)
            }

            override fun onFailed(call: Call<MovieResponse>, error: Throwable) {
                Log.e(TAG, error.printStackTrace().toString())
            }
        })
        return liveMovies
    }

    override fun getTVShows(): LiveData<List<TVShowEntity>> {
        remoteRepository.getTVShowsFromAPI(object :
            CallbackGetTVShows {
            override fun onSuccess(data: List<TVShowEntity>) {
                liveTVShows.postValue(data)
            }

            override fun onFailed(call: Call<TVShowResponse>, error: Throwable) {
                Log.e(TAG, error.printStackTrace().toString())
            }
        })
        return liveTVShows
    }

    override fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        remoteRepository.getMovieDetailFromAPI(id, object :
            CallbackGetDetailMovie {
            override fun onSuccess(data: DetailMovieResponse) {
                liveDetailMovie.postValue(data)
            }

            override fun onFailed(call: Call<DetailMovieResponse>, error: Throwable) {
                Log.e(TAG, error.printStackTrace().toString())
            }
        })
        return liveDetailMovie
    }

    override fun getDetailTvShow(id: String): LiveData<DetailTVShowResponse> {
        remoteRepository.getTvShowDetailFromAPI(id, object :
            CallbackGetDetailTv {
            override fun onSuccess(data: DetailTVShowResponse) {
                liveDetailTv.postValue(data)
            }

            override fun onFailed(call: Call<DetailTVShowResponse>, error: Throwable) {
                Log.e(TAG, error.printStackTrace().toString())
            }
        })
        return liveDetailTv
    }

}