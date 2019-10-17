package com.example.moviecatalogue.main.tvshow


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
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.jetbrains.anko.support.v4.startActivity

class TVShowFragment : Fragment() {

    private lateinit var filmAdapter: FilmAdapter
    private lateinit var tvShowViewModel: TVShowViewModel
    private var data = mutableListOf<FilmEntity>()

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
            tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
            data = tvShowViewModel.getTVShows()

            filmAdapter = FilmAdapter(requireContext(), data) {
                startActivity<DetailActivity>(DetailActivity.DATA_EXTRA  to it)
            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = filmAdapter
        }

    }

}
