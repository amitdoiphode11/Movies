package com.eaglesoft.movies.framework.list.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.eaglesoft.movies.R
import com.eaglesoft.movies.business.network.model.WeMovie
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviePagedAdapter(val mLister: OnItemClick?) :
    PagingDataAdapter<WeMovie, MoviePagedAdapter.ViewHolder>(DataDifferentiators) {
    private val TAG = "ImagesListAdapter"


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false),
            parent.context
        )
    }

    inner class ViewHolder(val view: View, private val context: Context) :
        RecyclerView.ViewHolder(view) {
        fun onBind(movie: WeMovie?) {
            val path = "${context.getString(R.string.base_image_path)}${movie?.poster_path}"
            view.iv_movie_poster.load(path) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                scale(Scale.FILL)
            }
            view.tv_title.text = movie?.title
            view.setOnClickListener { mLister?.onMovieItemClicked(movie) }
        }
    }

    object DataDifferentiators : DiffUtil.ItemCallback<WeMovie>() {

        override fun areItemsTheSame(oldItem: WeMovie, newItem: WeMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeMovie, newItem: WeMovie): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClick {
        fun onMovieItemClicked(movie: WeMovie?)
    }


}