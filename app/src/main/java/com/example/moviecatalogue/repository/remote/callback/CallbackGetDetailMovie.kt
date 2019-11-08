package com.example.moviecatalogue.repository.remote.callback

import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import retrofit2.Call

interface CallbackGetDetailMovie {
    fun onSuccess(data: DetailMovieResponse)
    fun onFailed(call: Call<DetailMovieResponse>, error: Throwable)
}