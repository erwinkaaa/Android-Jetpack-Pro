package com.example.moviecatalogue.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.moviecatalogue.R
import com.example.moviecatalogue.entity.FilmEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val DATA_EXTRA = "DATA_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = resources.getString(R.string.detail_film)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        val data = intent.getParcelableExtra<FilmEntity>(DATA_EXTRA)

        if (data != null) {
            detailViewModel.setDataDetail(data)
            Picasso.get().load(detailViewModel.poster).into(iv_poster)
            tv_title.text = detailViewModel.title
            tv_releasedate.text = detailViewModel.releaseDate
            tv_durationtime.text = detailViewModel.durationTime
            tv_distributedby.text = detailViewModel.distributedBy
            tv_description.text = detailViewModel.description
        }
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
