package com.example.pruebaandroid.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruebaandroid.R
import com.example.pruebaandroid.databinding.ActivityMainBinding
import com.example.pruebaandroid.model.PopularMovie
import com.example.pruebaandroid.view.adapters.PosterMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        with(binding) {
            recyclerView.adapter = PosterMovieAdapter(
                listOf(
                    PopularMovie(0, "1", ""),
                    PopularMovie(0, "2", ""),
                    PopularMovie(0, "3", ""),
                    PopularMovie(0, "4", "")
                )
            )
        }
    }
}