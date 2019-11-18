package com.example.moviecatalogue.ui.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.repository.local.entity.TvEntity
import com.example.moviecatalogue.repository.remote.api.ApiRepository
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_film.view.*

class TVShowAdapter(
    private val context: Context,
    private val data: MutableList<TvEntity>,
    private val onClickListener: (TvEntity) -> Unit
) :
    RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {

    fun setData(data: List<TvEntity>) {
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
            data: TvEntity,
            onClickListener: (TvEntity) -> Unit
        ) {
            itemView.tv_title.text = data.name
            Picasso.get().load(ApiRepository.IMAGE_URL + data.poster_path).into(itemView.iv_poster)
            containerView.setOnClickListener { onClickListener(data) }
        }
    }

}