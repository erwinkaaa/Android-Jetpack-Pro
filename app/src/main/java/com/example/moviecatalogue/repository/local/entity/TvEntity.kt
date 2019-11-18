package com.example.moviecatalogue.repository.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviecatalogue.repository.local.map.TvMap
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TvMap.tableName)
data class TvEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = TvMap.id)
    var id: String,

    @NonNull
    @ColumnInfo(name = TvMap.name)
    var name: String,

    @NonNull
    @ColumnInfo(name = TvMap.poster_path)
    var poster_path: String,

    @NonNull
    @ColumnInfo(name = TvMap.favorite)
    var favorite: Boolean = false,

    @NonNull
    @ColumnInfo(name = TvMap.first_air_date)
    var first_air_date: String,

    @NonNull
    @ColumnInfo(name = TvMap.overview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = TvMap.vote_average)
    var vote_average: Double,

    @NonNull
    @ColumnInfo(name = TvMap.popularity)
    var popularity: Double


) : Parcelable