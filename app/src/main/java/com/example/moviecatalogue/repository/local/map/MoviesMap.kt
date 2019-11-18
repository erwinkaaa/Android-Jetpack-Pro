package com.example.moviecatalogue.repository.local.map

// prevent double string hardcoded, once in interface, once in entity, i think also helps when column name changes

object MoviesMap {
    const val tableName = "movies"

    const val id = "id"
    const val title = "title"
    const val poster_path = "poster_path"
    const val favorite = "favorite"
    const val release_date = "release_date"
    const val overview = "overview"
    const val popularity = "popularity"
    const val vote_average = "vote_average"

}