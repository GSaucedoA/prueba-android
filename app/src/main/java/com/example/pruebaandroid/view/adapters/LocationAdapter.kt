package com.example.pruebaandroid.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaandroid.databinding.CustomLocationItemBinding
import com.example.pruebaandroid.model.Location
import java.text.DateFormat

class LocationAdapter(private val onItemClicked: (Location) -> Unit) :
    ListAdapter<Location, LocationAdapter.LocationViewHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val viewHolder = LocationViewHolder(
            CustomLocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        return viewHolder
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(private val binding: CustomLocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            with(binding) {
                cityName.text = location.cityName
                addedDate.text = DateFormat.getInstance().format(location.date)
                seeInMapBtn.setOnClickListener { onItemClicked(location) }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.latitude == newItem.latitude && oldItem.longitude == newItem.longitude
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem == newItem
            }
        }
    }
}