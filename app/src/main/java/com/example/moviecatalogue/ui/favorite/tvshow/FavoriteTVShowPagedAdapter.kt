package com.example.moviecatalogue.ui.favorite.tvshow

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_film.view.*


class FavoriteTVShowPagedAdapter(
    private val context: Context,
    private val onClickListener: (TvEntity) -> Unit
) : PagedListAdapter<TvEntity, FavoriteTVShowPagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.inflater_film, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItem(position) != null) {
            val movie = getItem(position)!!
            holder.bindItem(movie, onClickListener)
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(
            data: TvEntity,
            onClickListener: (TvEntity) -> Unit
        ) {
            itemView.tv_title.text = data.name
            Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(itemView.iv_poster)
            containerView.setOnClickListener { onClickListener(data) }
        }
    }
}