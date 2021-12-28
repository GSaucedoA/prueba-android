package com.example.pruebaandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaandroid.databinding.CustomMoviePosterBinding
import com.example.pruebaandroid.model.PopularMovie

class PosterMovieAdapter(private val movieList: List<PopularMovie>) :
    RecyclerView.Adapter<PosterMovieAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: CustomMoviePosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PopularMovie) {
            with(binding) {
                with(movie) {
                    movieTitle.text = title
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
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size
}