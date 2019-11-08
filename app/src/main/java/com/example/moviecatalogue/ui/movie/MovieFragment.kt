package com.example.moviecatalogue.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.remote.response.movie.MovieEntity
import com.example.moviecatalogue.ui.MainActivity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity

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
            movieViewModel = obtainViewModel()
            movieViewModel.getLiveDataMovies().observe(this, observerMovies)

            movieAdapter = MovieAdapter(requireContext(), data) {
                startActivity<DetailActivity>(
                    DetailActivity.TYPE_EXTRA to DetailActivity.MOVIE,
                    DetailActivity.ID_EXTRA to it.id
                )
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = movieAdapter
            }
        }
    }

    private fun obtainViewModel(): MovieViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(requireActivity(), factory).get(MovieViewModel::class.java)
    }

    private val observerMovies = Observer<List<MovieEntity>> {
        if (it != null) {
            movieAdapter.setData(it)
            progress_circular.invisible()
        }
    }
}
