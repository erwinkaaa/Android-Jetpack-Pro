package com.example.moviecatalogue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.movie.MovieFragment
import com.example.moviecatalogue.ui.tvshow.TVShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val INSTANCE = "INSTANCE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.movies -> {
                    loadMovieFragment()
                }
                R.id.tvshows -> {
                    loadTvShowFragment()
                }
            }
            true
        }

        if (savedInstanceState == null) {
            bottom_navigation.selectedItemId = R.id.movies
        } else {
            when (savedInstanceState.getString(INSTANCE)) {
                MovieFragment::class.java.simpleName -> {
                    bottom_navigation.selectedItemId = R.id.movies
                }
                TVShowFragment::class.java.simpleName -> {
                    bottom_navigation.selectedItemId = R.id.tvshows
                }
            }
        }
    }

    private fun loadMovieFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame,
                MovieFragment(), MovieFragment::class.java.simpleName)
            .commit()
    }

    private fun loadTvShowFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame,
                TVShowFragment(), TVShowFragment::class.java.simpleName)
            .commit()
    }
}
