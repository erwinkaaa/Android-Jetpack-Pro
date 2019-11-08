package com.example.moviecatalogue.repository.remote

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.example.moviecatalogue.repository.remote.callback.CallbackGetDetailMovie
import com.example.moviecatalogue.repository.remote.callback.CallbackGetDetailTv
import com.example.moviecatalogue.repository.remote.callback.CallbackGetMovies
import com.example.moviecatalogue.repository.remote.callback.CallbackGetTVShows
import com.example.moviecatalogue.util.IdleResource
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import com.example.moviecatalogue.repository.remote.response.movie.MovieResponse
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import com.example.moviecatalogue.repository.remote.response.tv.TVShowResponse
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteRepository {

    open fun getMoviesFromAPI(callbackGetMovies: CallbackGetMovies) {
        IdleResource.increment()
        doAsync {
            ApiRepository.retrofitApiServiceInstance().loadMovies(BuildConfig.TSDB_API_KEY).enqueue(object :
                Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!
                            val movies = mutableListOf<MovieEntity>()
                            for (i in data.results.indices) {
                                movies.add(
                                    MovieEntity(
                                        data.results[i].id,
                                        data.results[i].title,
                                        data.results[i].poster_path
                                    )
                                )
                            }
                            callbackGetMovies.onSuccess(movies)
                            IdleResource.decrement()
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, error: Throwable) {
                    callbackGetMovies.onFailed(call, error)
                }
            })
        }
    }

    open fun getTVShowsFromAPI(callbackGetTVShows: CallbackGetTVShows) {
        IdleResource.increment()
        doAsync {
            ApiRepository.retrofitApiServiceInstance().loadTVShows(BuildConfig.TSDB_API_KEY).enqueue(object :
                Callback<TVShowResponse> {
                override fun onResponse(
                    call: Call<TVShowResponse>,
                    response: Response<TVShowResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!
                            val tv = mutableListOf<TVShowEntity>()
                            for (i in data.results.indices) {
                                tv.add(
                                    TVShowEntity(
                                        data.results[i].id,
                                        data.results[i].name,
                                        data.results[i].poster_path
                                    )
                                )
                            }
                            callbackGetTVShows.onSuccess(tv)
                            IdleResource.decrement()
                        }
                    }
                }

                override fun onFailure(call: Call<TVShowResponse>, error: Throwable) {
                    callbackGetTVShows.onFailed(call, error)
                }
            })
        }
    }

    open fun getMovieDetailFromAPI(id: String, callbackGetDetailMovie: CallbackGetDetailMovie) {
        IdleResource.increment()
        doAsync {
            ApiRepository.retrofitApiServiceInstance().loadMovieDetail(id, BuildConfig.TSDB_API_KEY).enqueue(object :
                Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!
                            callbackGetDetailMovie.onSuccess(data)
                            IdleResource.decrement()
                        }
                    }
                }

                override fun onFailure(call: Call<DetailMovieResponse>, error: Throwable) {
                    callbackGetDetailMovie.onFailed(call, error)
                }
            })
        }
    }

    open fun getTvShowDetailFromAPI(id: String, callbackGetDetailTv: CallbackGetDetailTv) {
        IdleResource.increment()
        doAsync {
            ApiRepository.retrofitApiServiceInstance().loadTVShowDetail(id, BuildConfig.TSDB_API_KEY).enqueue(object :
                Callback<DetailTVShowResponse> {
                override fun onResponse(
                    call: Call<DetailTVShowResponse>,
                    response: Response<DetailTVShowResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!
                            callbackGetDetailTv.onSuccess(data)
                            IdleResource.decrement()
                        }
                    }
                }

                override fun onFailure(call: Call<DetailTVShowResponse>, error: Throwable) {
                    callbackGetDetailTv.onFailed(call, error)
                }
            })
        }
    }

}