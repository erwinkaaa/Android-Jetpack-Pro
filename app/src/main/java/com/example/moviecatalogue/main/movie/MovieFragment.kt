package com.example.moviecatalogue.main.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.detail.DetailActivity
import com.example.moviecatalogue.entity.FilmEntity
import com.example.moviecatalogue.main.FilmAdapter
import com.example.moviecatalogue.main.MainActivity
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity

class MovieFragment : Fragment() {

    private lateinit var filmAdapter: FilmAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var data = mutableListOf<FilmEntity>()

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
            movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
            data = movieViewModel.getMovies()

            filmAdapter = FilmAdapter(requireContext(), data) {
                startActivity<DetailActivity>(DetailActivity.DATA_EXTRA  to it)
            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = filmAdapter
        }
    }
}
