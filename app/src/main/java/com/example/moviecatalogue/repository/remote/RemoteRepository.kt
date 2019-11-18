package com.example.moviecatalogue.repository.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.example.moviecatalogue.repository.remote.response.movie.MovieModel
import com.example.moviecatalogue.repository.remote.response.movie.MovieResponse
import com.example.moviecatalogue.repository.remote.response.tv.TVShowResponse
import com.example.moviecatalogue.repository.remote.response.tv.TvModel
import com.example.moviecatalogue.util.IdleResource
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class RemoteRepository {

    open fun getMoviesFromAPI() : LiveData<ApiResponse<List<MovieModel>>> {
        val liveMovies = MutableLiveData<ApiResponse<List<MovieModel>>>()
        IdleResource.increment()
        doAsync {
            ApiRepository.retrofitApiServiceInstance().loadMovies(BuildConfig.TSDB_API_KEY).enqueue(object :
                Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!
                            val movies = mutableListOf<MovieModel>()
                            for (i in data.results.indices) {
                                movies.add(
                                    MovieModel(
                                        data.results[i].id,
                                        data.results[i].title,
                                        data.results[i].poster_path,
                                        data.results[i].release_date,
                                        data.results[i].overview,
                                        data.results[i].vote_average,
                                        data.results[i].popularity
                                    )
                                )
                            }
                            liveMovies.value = ApiResponse.success(movies)
                            IdleResource.decrement()
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, error: Throwable) {
                    error.printStackTrace()
                }
            })
        }
        return liveMovies
    }

    open fun getTVShowsFromAPI() : LiveData<ApiResponse<List<TvModel>>> {
        val liveTv = MutableLiveData<ApiResponse<List<TvModel>>>()
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
                            val tv = mutableListOf<TvModel>()
                            for (i in data.results.indices) {
                                tv.add(
                                    TvModel(
                                        data.results[i].id,
                                        data.results[i].name,
                                        data.results[i].poster_path,
                                        data.results[i].first_air_date,
                                        data.results[i].overview,
                                        data.results[i].vote_average,
                                        data.results[i].popularity
                                    )
                                )
                            }
                            liveTv.value = ApiResponse.success(tv)
                            IdleResource.decrement()
                        }
                    }
                }

                override fun onFailure(call: Call<TVShowResponse>, error: Throwable) {
                    error.printStackTrace()
                }
            })
        }
        return liveTv
    }

}