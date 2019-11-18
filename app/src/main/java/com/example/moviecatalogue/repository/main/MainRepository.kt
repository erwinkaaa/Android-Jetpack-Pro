package com.example.moviecatalogue.repository.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.repository.local.LocalRepository
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.remote.ApiResponse
import com.example.moviecatalogue.repository.remote.RemoteRepository
import com.example.moviecatalogue.repository.remote.response.movie.MovieModel
import com.example.moviecatalogue.repository.remote.response.tv.TvModel
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.util.AppExecutors


open class MainRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
) : MainDataSource {

    private val pageSize = 5

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {

        return object : NetworkBoundResource<List<MovieEntity>, List<MovieModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localRepository.getMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>> {
                return remoteRepository.getMoviesFromAPI()
            }

            override fun saveCallResult(data: List<MovieModel>) {
                val movies = mutableListOf<MovieEntity>()
                for (i in data.indices) {
                    movies.add(
                        MovieEntity(
                            data[i].id,
                            data[i].title,
                            data[i].poster_path,
                            false,
                            data[i].release_date,
                            data[i].overview,
                            data[i].vote_average,
                            data[i].popularity
                        )
                    )
                }
                localRepository.insertMovies(movies)
            }
        }.asLiveData()
    }

    override fun getTVShows(): LiveData<Resource<List<TvEntity>>> {

        return object : NetworkBoundResource<List<TvEntity>, List<TvModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvEntity>> {
                return localRepository.getTvShows()
            }

            override fun shouldFetch(data: List<TvEntity>): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvModel>>> {
                return remoteRepository.getTVShowsFromAPI()
            }

            override fun saveCallResult(data: List<TvModel>) {
                val movies = mutableListOf<TvEntity>()
                for (i in data.indices) {
                    movies.add(
                        TvEntity(
                            data[i].id,
                            data[i].name,
                            data[i].poster_path,
                            false,
                            data[i].first_air_date,
                            data[i].overview,
                            data[i].vote_average,
                            data[i].popularity
                        )
                    )
                }
                localRepository.insertTvShows(movies)
            }
        }.asLiveData()
    }

    override fun getFavoriteMoviesPaged(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(localRepository.getFavoriteMoviesPaged(), pageSize).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModel>>> {
                return MutableLiveData<ApiResponse<List<MovieModel>>>()
            }

            override fun saveCallResult(data: List<MovieModel>) {

            }
        }.asLiveData()
    }

    override fun getFavoriteTVShowsPaged(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                return LivePagedListBuilder(localRepository.getFavoriteTvShowsPaged(), pageSize).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<TvModel>>> {
                return MutableLiveData<ApiResponse<List<TvModel>>>()
            }

            override fun saveCallResult(data: List<TvModel>) {

            }
        }.asLiveData()
    }

    override fun setMovieFavoriteState(movieEntity: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localRepository.setMovieFavoriteState(movieEntity, state)
        }
    }

    override fun setTvFavoriteState(tvEntity: TvEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localRepository.setTvFavoriteState(tvEntity, state)
        }
    }
}