package com.example.moviecatalogue.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.example.moviecatalogue.ui.favorite.tvshow.FavoriteTVShowViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvshow.TVShowViewModel


class ViewModelFactory(private val mainRepository: MainRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private lateinit var instance: ViewModelFactory
        fun getInstance(application: Application): ViewModelFactory {
            synchronized(ViewModelFactory::class.java) {
                instance =
                    ViewModelFactory(Injection.provideRepository(application))
            }
            return instance
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            MovieViewModel::class.java -> {
                return MovieViewModel(mainRepository) as T
            }
            TVShowViewModel::class.java -> {
                return TVShowViewModel(mainRepository) as T
            }
            DetailViewModel::class.java -> {
                return DetailViewModel(mainRepository) as T
            }
            FavoriteMovieViewModel::class.java -> {
                return FavoriteMovieViewModel(mainRepository) as T
            }
            FavoriteTVShowViewModel::class.java -> {
                return FavoriteTVShowViewModel(mainRepository) as T
            }
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}