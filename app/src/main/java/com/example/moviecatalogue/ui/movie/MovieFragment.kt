package com.example.moviecatalogue.ui.movie


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
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.vo.Status
import com.example.moviecatalogue.ui.MainActivity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.util.ViewModelFactory
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var data = mutableListOf<MovieEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(MainActivity.INSTANCE, MovieFragment::class.java.simpleName)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            movieViewModel = obtainViewModel(activity!!)
            movieViewModel.insertBait()
            movieViewModel.movies.observe(this, Observer {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            progress_circular.visible()
                        }
                        Status.SUCCESS -> {
                            progress_circular.invisible()
                            movieAdapter.setData(it.data!!)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progress_circular.invisible()
                            toast("Terjadi kesalahan")
                        }
                    }
                }
            })

            movieAdapter = MovieAdapter(requireContext(), data) {
                startActivity<DetailActivity>(
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

    private fun obtainViewModel(fragmentActivity: FragmentActivity): MovieViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProviders.of(requireActivity(), factory).get(MovieViewModel::class.java)
    }
}
