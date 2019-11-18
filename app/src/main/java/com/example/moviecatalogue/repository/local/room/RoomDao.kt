package com.example.moviecatalogue.repository.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.local.map.MoviesMap
import com.example.moviecatalogue.repository.local.map.TvMap
import androidx.paging.DataSource

@Dao
interface RoomDao {

    @WorkerThread
    @Query("SELECT * FROM ${MoviesMap.tableName}")
    fun getMovies(): LiveData<List<MovieEntity>>

    @WorkerThread
    @Query("SELECT * FROM ${MoviesMap.tableName} WHERE ${MoviesMap.favorite} = 1")
    fun getFavoriteMoviesAsPaged(): DataSource.Factory<Int, MovieEntity>

    @WorkerThread
    @Query("SELECT * FROM ${TvMap.tableName}")
    fun getTVShows(): LiveData<List<TvEntity>>

    @WorkerThread
    @Query("SELECT * FROM ${TvMap.tableName} WHERE ${TvMap.favorite} = 1")
    fun getFavoriteTVShowsAsPaged(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(data: List<MovieEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movieEntity: MovieEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(data: List<TvEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTv(tvEntity: TvEntity): Int

}