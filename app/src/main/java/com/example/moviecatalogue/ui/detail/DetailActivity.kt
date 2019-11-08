package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.example.moviecatalogue.repository.remote.response.detail.DetailMovieResponse
import com.example.moviecatalogue.repository.remote.response.detail.DetailTVShowResponse
import com.example.moviecatalogue.util.ViewModelFactory
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val ID_EXTRA = "ID_EXTRA"
        const val TYPE_EXTRA = "TYPE_EXTRA"
        const val MOVIE = "MOVIE"
        const val TV = "TV"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = resources.getString(R.string.detail_film)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        layout_detail.invisible()

        if (intent.hasExtra(ID_EXTRA) && intent.hasExtra(TYPE_EXTRA)) {
            val id = intent.getStringExtra(ID_EXTRA)!!

            detailViewModel = obtainViewModel()
            when (intent.getStringExtra(TYPE_EXTRA)) {
                MOVIE -> {
                    detailViewModel.getLiveDataDetailMovie(id).observe(this, observerDetailMovie)
                }
                TV -> {
                    detailViewModel.getLiveDataDetailTVShow(id).observe(this, observerDetailTVShow)
                }
            }
        }
    }

    private fun obtainViewModel(): DetailViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    private val observerDetailMovie = Observer<DetailMovieResponse> {
        if (it != null) {
            initDataMovie(it)
            layout_detail.visible()
            progress_circular.invisible()
        }
    }

    private val observerDetailTVShow = Observer<DetailTVShowResponse> {
        if (it != null) {
            initDataTv(it)
            layout_detail.visible()
            progress_circular.invisible()
        }
    }

    private fun initDataMovie(data: DetailMovieResponse) {
        Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(iv_poster)
        tv_title.text = data.title
        tv_releasedate.text = data.release_date
        tv_durationtime.text = String.format("${data.runtime} %1s", "minutes")

        var companies = ""
        for (i in data.production_companies.indices) {
            var comma = ", "
            if (i == data.production_companies.size - 1) {
                comma = ""
            }
            companies += (data.production_companies[i].name + comma)
        }

        tv_distributedby.text = companies
        tv_description.text = data.overview
    }

    private fun initDataTv(data: DetailTVShowResponse) {
        Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(iv_poster)
        tv_title.text = data.name
        tv_releasedate.text = data.first_air_date

        val runtime = if (data.episode_run_time.size > 1) {
            "${data.episode_run_time[0]} - ${data.episode_run_time[1]} minutes"
        } else {
            "${data.episode_run_time[0]} minutes"
        }

        tv_durationtime.text = runtime

        var companies = ""
        for (i in data.production_companies.indices) {
            var comma = ", "
            if (i == data.production_companies.size - 1) {
                comma = ""
            }
            companies += (data.production_companies[i].name + comma)
        }

        tv_distributedby.text = companies
        tv_description.text = data.overview
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
