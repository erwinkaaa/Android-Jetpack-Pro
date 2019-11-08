package com.example.moviecatalogue.repository.remote.callback

import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import retrofit2.Call

interface CallbackGetDetailTv {
    fun onSuccess(data: DetailTVShowResponse)
    fun onFailed(call: Call<DetailTVShowResponse>, error: Throwable)
}