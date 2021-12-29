package com.example.pruebaandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebaandroid.databinding.CustomMoviePosterBinding
import com.example.pruebaandroid.model.PopularMovie
import com.example.pruebaandroid.model.posterUrl

class PosterMovieAdapter :
    ListAdapter<PopularMovie, PosterMovieAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(private val binding: CustomMoviePosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PopularMovie) {
            with(binding) {
                with(movie) {
                    movieTitle.text = title
                    Glide.with(binding.root).load(posterUrl())
                        .into(moviePoster)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomMoviePosterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PopularMovie>() {
            override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
                return oldItem == newItem
            }

        }
    }
}