package com.example.moviecatalogue.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity


@Database(
    entities = [MovieEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        private const val dbName = "movie_catalogue.db"
        private lateinit var INSTANCE: RoomDB

        fun getDatabase(context: Context): RoomDB {
            synchronized(RoomDB::class.java) {
                INSTANCE = Room.databaseBuilder(context, RoomDB::class.java, dbName).build()
            }
            return INSTANCE
        }
    }

}