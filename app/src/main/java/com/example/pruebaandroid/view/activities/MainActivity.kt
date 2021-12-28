package com.example.pruebaandroid.view.activities

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.window.layout.WindowMetricsCalculator
import com.example.pruebaandroid.databinding.ActivityMainBinding
import com.example.pruebaandroid.model.PopularMovie
import com.example.pruebaandroid.view.adapters.PosterMovieAdapter
import com.example.pruebaandroid.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val popularMovieList = mutableListOf<PopularMovie>()

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateSpanCount()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.response.observe(this) {
            updateRecyclerView(it.popularMovieList)
        }
    }

    private fun updateRecyclerView(list: List<PopularMovie>) {
        val startFrom = popularMovieList.size
        popularMovieList.addAll(list)
        binding.recyclerView.adapter?.notifyItemRangeChanged(startFrom, list.size)
    }

    private fun setUpRecyclerView() {
        with(binding) {
            recyclerView.adapter = PosterMovieAdapter(popularMovieList)
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