package com.example.moviecatalogue.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.local.entity.MovieEntity
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_film.view.*

class MovieAdapter(
    private val context: Context,
    private val data: MutableList<MovieEntity>,
    private val onClickListener: (MovieEntity) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    fun setData(data: List<MovieEntity>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.inflater_film, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], onClickListener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(
            data: MovieEntity,
            onClickListener: (MovieEntity) -> Unit
        ) {
            itemView.tv_title.text = data.title
            Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(itemView.iv_poster)
            containerView.setOnClickListener { onClickListener(data) }
        }
    }

}