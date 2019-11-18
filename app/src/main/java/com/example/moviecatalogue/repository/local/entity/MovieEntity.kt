package com.example.moviecatalogue.repository.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviecatalogue.repository.local.map.MoviesMap
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = MoviesMap.tableName)
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = MoviesMap.id)
    var id: String,

    @NonNull
    @ColumnInfo(name = MoviesMap.title)
    var title: String,

    @NonNull
    @ColumnInfo(name = MoviesMap.poster_path)
    var poster_path: String,

    @NonNull
    @ColumnInfo(name = MoviesMap.favorite)
    var favorite: Boolean = false,

    @NonNull
    @ColumnInfo(name = MoviesMap.release_date)
    var release_date: String,

    @NonNull
    @ColumnInfo(name = MoviesMap.overview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = MoviesMap.vote_average)
    var vote_average: Double,

    @NonNull
    @ColumnInfo(name = MoviesMap.popularity)
    var popularity: Double

) : Parcelable