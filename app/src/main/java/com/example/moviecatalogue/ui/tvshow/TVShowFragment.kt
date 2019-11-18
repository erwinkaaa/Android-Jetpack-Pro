package com.example.moviecatalogue.ui.tvshow


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
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.vo.Status
import com.example.moviecatalogue.ui.MainActivity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.util.ViewModelFactory
import com.example.moviecatalogue.util.invisible
import com.example.moviecatalogue.util.visible
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class TVShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TVShowAdapter
    private lateinit var tvShowViewModel: TVShowViewModel
    private var data = mutableListOf<TvEntity>()

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
            tvShowViewModel = obtainViewModel(activity!!)
            tvShowViewModel.insertBait()
            tvShowViewModel.tv.observe(this, Observer {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            progress_circular.visible()
                        }
                        Status.SUCCESS -> {
                            progress_circular.invisible()
                            tvShowAdapter.setData(it.data!!)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progress_circular.invisible()
                            toast("Terjadi kesalahan")
                        }
                    }
                }
            })

            tvShowAdapter = TVShowAdapter(requireContext(), data) {
                startActivity<DetailActivity>(
                    DetailActivity.TYPE_EXTRA to DetailActivity.TV,
                    DetailActivity.DATA_EXTRA to it
                )
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }

    }

    private fun obtainViewModel(fragmentActivity: FragmentActivity): TVShowViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProviders.of(requireActivity(), factory).get(TVShowViewModel::class.java)
    }
}
