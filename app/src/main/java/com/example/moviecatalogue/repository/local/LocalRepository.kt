package com.example.moviecatalogue.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.local.room.RoomDB
import com.example.moviecatalogue.repository.local.room.RoomDao
import java.util.concurrent.Executors

open class LocalRepository(context: Context) {

    private val roomDao: RoomDao = RoomDB.getDatabase(context).roomDao()

    private var executorService = Executors.newSingleThreadExecutor()

    open fun getMovies(): LiveData<List<MovieEntity>> {
        return roomDao.getMovies()
    }

    open fun getFavoriteMoviesPaged(): DataSource.Factory<Int, MovieEntity> {
        return roomDao.getFavoriteMoviesAsPaged()
    }

    open fun getTvShows(): LiveData<List<TvEntity>> {
        return roomDao.getTVShows()
    }

    open fun getFavoriteTvShowsPaged(): DataSource.Factory<Int, TvEntity> {
        return roomDao.getFavoriteTVShowsAsPaged()
    }

    fun insertMovies(data: List<MovieEntity>) {
        executorService.execute {
            roomDao.insertMovies(data)
        }
    }

    fun insertTvShows(data: List<TvEntity>) {
        executorService.execute {
            roomDao.insertTvShows(data)
        }
    }

    fun setMovieFavoriteState(movieEntity: MovieEntity, state: Boolean) {
        movieEntity.favorite = state
        roomDao.updateMovie(movieEntity)
    }

    fun setTvFavoriteState(tvEntity: TvEntity, state: Boolean) {
        tvEntity.favorite = state
        roomDao.updateTv(tvEntity)
    }

}