package com.example.pruebaandroid.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebaandroid.databinding.FragmentGoogleMapsHistoryBinding
import com.example.pruebaandroid.model.Location
import com.example.pruebaandroid.view.adapters.LocationAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

class GoogleMapsHistoryFragment : Fragment() {

    private lateinit var binding: FragmentGoogleMapsHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoogleMapsHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpGoogleMap(savedInstanceState)
    }

    private fun setUpRecyclerView(map: GoogleMap) {
        binding.recyclerView.apply {
            adapter = LocationAdapter {
                val place = LatLng(it.latitude, it.longitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 15f))
            }
        }
    }

    private fun fetchfromFirebase(map: GoogleMap) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Location").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val list: List<Location> = it.result.toObjects(Location::class.java)
                setUpRecyclerView(map)
                (binding.recyclerView.adapter as LocationAdapter).submitList(list)
                for (i in 0..list.size - 2) {
                    addMarker(map, list[i])
                }
                val lastValue = list.last()
                map.moveCamera(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            lastValue.latitude,
                            lastValue.longitude
                        )
                    )
                )
            } else {
                Log.e("database:error", it.exception?.message.toString())
            }
        }
    }

    private fun addMarker(map: GoogleMap, location: Location) {
        val name = location.cityName
        val place = LatLng(location.latitude, location.longitude)
        map.apply {
            addMarker(MarkerOptions().position(place).title(name))
        }
    }

    private fun setUpGoogleMap(savedInstanceState: Bundle?) {
        binding.googleMap.apply {
            onCreate(savedInstanceState)
            onResume()
            getMapAsync { map ->
                fetchfromFirebase(map)
            }
        }
    }
}