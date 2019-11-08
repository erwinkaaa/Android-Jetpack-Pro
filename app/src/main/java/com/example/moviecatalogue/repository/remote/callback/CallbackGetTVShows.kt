package com.example.moviecatalogue.repository.remote.callback

import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import com.example.moviecatalogue.repository.remote.response.tv.TVShowResponse
import retrofit2.Call

interface CallbackGetTVShows {
    fun onSuccess(data: List<TVShowEntity>)
    fun onFailed(call: Call<TVShowResponse>, error: Throwable)
}