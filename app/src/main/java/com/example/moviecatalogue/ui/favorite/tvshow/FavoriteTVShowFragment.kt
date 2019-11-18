package com.example.moviecatalogue.ui.favorite.tvshow


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
import kotlinx.android.synthetic.main.fragment_favorite_tv.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast


class FavoriteTVShowFragment : Fragment() {

    private lateinit var tvShowAdapter: FavoriteTVShowPagedAdapter
    private lateinit var tvShowViewModel: FavoriteTVShowViewModel

    companion object {
        const val REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            tvShowViewModel = obtainViewModel(activity!!)

            loadFavTv()

            tvShowAdapter = FavoriteTVShowPagedAdapter(requireContext()) {
                startActivityForResult<DetailActivity>(REQUEST_CODE,
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

    private fun obtainViewModel(fragmentActivity: FragmentActivity): FavoriteTVShowViewModel {
        val factory = ViewModelFactory.getInstance(fragmentActivity.application)
        return ViewModelProviders.of(requireActivity(), factory).get(FavoriteTVShowViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                loadFavTv()
            }
        }
    }

    private fun loadFavTv() {
        tvShowViewModel.insertBait()
        tvShowViewModel.favoriteTv.observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> {
                        progress_circular.visible()
                    }
                    Status.SUCCESS -> {
                        progress_circular.invisible()
                        if (it.data!!.isNotEmpty()) {
                            tvShowAdapter.submitList(it.data)
                            tv_notice_empty.invisible()
                            recyclerView.visible()
                        } else {
                            tv_notice_empty.visible()
                            recyclerView.invisible()
                        }
                        tvShowAdapter.notifyDataSetChanged()
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
