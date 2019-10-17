package com.example.moviecatalogue.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmEntity (
    val title: String,
    val description: String,
    val poster: Int,
    val releaseDate: String,
    val runningTime: String,
    val distributedBy: String
) : Parcelable