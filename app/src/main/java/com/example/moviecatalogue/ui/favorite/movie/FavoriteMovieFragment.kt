package com.example.moviecatalogue.ui.favorite.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.vo.Status
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.RESULT_CODE
import com.example.moviecatalogue.util.ViewModelFactory
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.visible
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast

class FavoriteMovieFragment : Fragment() {

    private lateinit var movieAdapter: FavoriteMoviePagedAdapter
    private lateinit var movieViewModel: FavoriteMovieViewModel

    companion object {
        const val REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            movieViewModel = obtainViewModel(activity!!)

            loadFavMovies()

            movieAdapter = FavoriteMoviePagedAdapter(requireContext()) {
                startActivityForResult<DetailActivity>(REQUEST_CODE,
                    DetailActivity.TYPE_EXTRA to DetailActivity.MOVIE,
                    DetailActivity.DATA_EXTRA to it
                )
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun obtainViewModel(fragmentActivity: FragmentActivity): FavoriteMovieViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProviders.of(requireActivity(), factory).get(FavoriteMovieViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                loadFavMovies()
            }
        }
    }

    private fun loadFavMovies() {
        movieViewModel.insertBait()
        movieViewModel.favoriteMovies.observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        progress_circular.visible()
                    }
                    Status.SUCCESS -> {
                        progress_circular.invisible()
                        if (it.data!!.isNotEmpty()) {
                            movieAdapter.submitList(it.data)
                            tv_notice_empty.invisible()
                            recyclerView.visible()
                        } else {
                            tv_notice_empty.visible()
                            recyclerView.invisible()
                        }
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progress_circular.invisible()
                        toast("Terjadi kesalahan")
                    }
                }
            }
        })
    }

}
