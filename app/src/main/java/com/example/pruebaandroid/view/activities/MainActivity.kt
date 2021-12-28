package com.example.pruebaandroid.view.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.window.layout.WindowMetricsCalculator
import com.example.pruebaandroid.R
import com.example.pruebaandroid.databinding.ActivityMainBinding
import com.example.pruebaandroid.model.PopularMovie
import com.example.pruebaandroid.view.adapters.PosterMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateSpanCount()
    }

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
                    PopularMovie(0, "4", ""),
                    PopularMovie(0, "1", ""),
                    PopularMovie(0, "2", ""),
                    PopularMovie(0, "3", ""),
                    PopularMovie(0, "4", ""),
                    PopularMovie(0, "1", ""),
                    PopularMovie(0, "2", ""),
                    PopularMovie(0, "3", ""),
                    PopularMovie(0, "4", "")
                )
            )
        }
        updateSpanCount()
    }

    private fun updateSpanCount() {
        (binding.recyclerView.layoutManager as GridLayoutManager).spanCount = getDynamicSpanCount()
    }

    private fun getDynamicSpanCount(): Int {
        val widthInDP: Float
        val density = resources.displayMetrics.density
        val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val currentBounds = windowMetrics.bounds
        val widthInPX = currentBounds.width()

        Toast.makeText(this, widthInPX.toString(), Toast.LENGTH_SHORT).show()

        widthInDP = widthInPX / density

        return (widthInDP / 189).toInt()
    }
}