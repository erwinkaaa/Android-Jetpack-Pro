package com.example.moviecatalogue.ui.detail

import android.app.Application
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.example.moviecatalogue.util.ViewModelFactory
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var movieDetail: MovieEntity
    private lateinit var tvDetail: TvEntity
    private lateinit var type: String
    private lateinit var menuItem: Menu

    companion object {
        const val DATA_EXTRA = "DATA_EXTRA"
        const val TYPE_EXTRA = "TYPE_EXTRA"
        const val MOVIE = "MOVIE"
        const val TV = "TV"
        const val RESULT_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = resources.getString(R.string.detail_film)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        layout_detail.invisible()

        if (intent.hasExtra(DATA_EXTRA) && intent.hasExtra(TYPE_EXTRA)) {

            detailViewModel = obtainViewModel(application)
            when (intent.getStringExtra(TYPE_EXTRA)) {
                MOVIE -> {
                    type = MOVIE
                    movieDetail = intent.getParcelableExtra(DATA_EXTRA)!!
                    detailViewModel.detailMovie.value = movieDetail
                    detailViewModel.detailMovie.observe(this, observerDetailMovie)
                }
                TV -> {
                    type = TV
                    tvDetail = intent.getParcelableExtra(DATA_EXTRA)!!
                    detailViewModel.detailTv.value = tvDetail
                    detailViewModel.detailTv.observe(this, observerDetailTVShow)
                }
            }
        }
    }

    private fun obtainViewModel(application: Application): DetailViewModel {
        val factory = ViewModelFactory.getInstance(application)
        return ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    private val observerDetailMovie = Observer<MovieEntity> {
        if (it != null) {
            initDataMovie(it)
            layout_detail.visible()
            progress_circular.invisible()
        }
    }

    private val observerDetailTVShow = Observer<TvEntity> {
        if (it != null) {
            initDataTv(it)
            layout_detail.visible()
            progress_circular.invisible()
        }
    }

    private fun initDataMovie(data: MovieEntity) {
        Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(iv_poster)
        tv_title.text = data.title
        tv_release_date.text = data.release_date
        tv_vote_average.text = data.vote_average.toString()
        tv_popularity.text = data.popularity.toString()
        tv_description.text = data.overview
    }

    private fun initDataTv(data: TvEntity) {
        Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(iv_poster)
        tv_title.text = data.name
        tv_release_date.text = data.first_air_date
        tv_vote_average.text = data.vote_average.toString()
        tv_popularity.text = data.popularity.toString()
        tv_description.text = data.overview
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        loadFavoriteState()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.state_favorite -> {
                setFavoriteState()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadFavoriteState() {
        when (type) {
            MOVIE -> {
                if (movieDetail.favorite) {
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                } else {
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                }
            }
            TV -> {
                if (tvDetail.favorite) {
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                } else {
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                }
            }
        }
    }

    private fun setFavoriteState() {
        when (type) {
            MOVIE -> {
                if (movieDetail.favorite) {
                    detailViewModel.setMovieFavoriteState(movieDetail, false)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                    toast(resources.getString(R.string.removed_from_favorite))
                } else {
                    detailViewModel.setMovieFavoriteState(movieDetail, true)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                    toast(resources.getString(R.string.added_to_favorite))
                }
            }
            TV -> {
                if (tvDetail.favorite) {
                    detailViewModel.setTvFavoriteState(tvDetail, false)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                    toast(resources.getString(R.string.removed_from_favorite))
                } else {
                    detailViewModel.setTvFavoriteState(tvDetail, true)
                    menuItem.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
                    toast(resources.getString(R.string.added_to_favorite))
                }
            }
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CODE)
        finish()
    }
}
