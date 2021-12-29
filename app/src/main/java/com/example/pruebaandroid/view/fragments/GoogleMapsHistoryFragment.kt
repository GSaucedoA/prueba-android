package com.example.pruebaandroid.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pruebaandroid.R
import com.example.pruebaandroid.databinding.FragmentGoogleMapsHistoryBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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

    private fun setUpGoogleMap(savedInstanceState: Bundle?) {
        binding.googleMap.apply {
            onCreate(savedInstanceState)
            onResume()
            getMapAsync(object : OnMapReadyCallback {
                override fun onMapReady(map: GoogleMap) {
                    val itspa = LatLng(19.54114297104868, -101.58399614481232)
                    map.apply {
                        addMarker(MarkerOptions().position(itspa).title("Marker"))
                        moveCamera(CameraUpdateFactory.newLatLngZoom(itspa, 15f))
                    }
                }

            })
        }
    }
}