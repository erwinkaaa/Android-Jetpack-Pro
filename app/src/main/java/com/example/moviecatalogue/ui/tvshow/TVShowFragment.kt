package com.example.moviecatalogue.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.remote.response.tv.TVShowEntity
import com.example.moviecatalogue.ui.MainActivity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.jetbrains.anko.support.v4.startActivity

class TVShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TVShowAdapter
    private lateinit var tvShowViewModel: TVShowViewModel
    private var data = mutableListOf<TVShowEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(MainActivity.INSTANCE, TVShowFragment::class.java.simpleName)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            tvShowViewModel = obtainViewModel()
            tvShowViewModel.getLiveDataMovies().observe(this, observerTVShows)

            tvShowAdapter = TVShowAdapter(requireContext(), data) {
                startActivity<DetailActivity>(
                    DetailActivity.TYPE_EXTRA to DetailActivity.TV,
                    DetailActivity.ID_EXTRA to it.id
                )
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tvShowAdapter
            }
        }

    }

    private fun obtainViewModel(): TVShowViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(requireActivity(), factory).get(TVShowViewModel::class.java)
    }

    private val observerTVShows = Observer<List<TVShowEntity>> {
        if (it != null) {
            tvShowAdapter.setData(it)
            progress_circular.invisible()
        }
    }
}
