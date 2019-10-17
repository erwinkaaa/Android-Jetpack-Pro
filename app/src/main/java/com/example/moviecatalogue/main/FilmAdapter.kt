package com.example.moviecatalogue.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.entity.FilmEntity
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inflater_film.view.*

class FilmAdapter(
    private val context: Context,
    private val data: MutableList<FilmEntity>,
    private val onClickListener: (FilmEntity) -> Unit
) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

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
            data: FilmEntity,
            onClickListener: (FilmEntity) -> Unit
        ) {
            itemView.tv_title.text = data.title
            Picasso.get().load(data.poster).into(itemView.iv_poster)
            containerView.setOnClickListener { onClickListener(data) }
        }
    }

}