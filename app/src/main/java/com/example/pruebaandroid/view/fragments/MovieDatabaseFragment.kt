package com.example.pruebaandroid.view.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.window.layout.WindowMetricsCalculator
import com.example.pruebaandroid.databinding.FragmentMovieDatabaseBinding
import com.example.pruebaandroid.model.PopularMovie
import com.example.pruebaandroid.view.adapters.PosterMovieAdapter
import com.example.pruebaandroid.view.viewmodel.MovieDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDatabaseFragment : Fragment() {
    private lateinit var binding: FragmentMovieDatabaseBinding
    private val viewModel: MovieDatabaseViewModel by viewModels()

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateSpanCount()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDatabaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.response.observe(viewLifecycleOwner) {
            updateRecyclerView(it)
        }
    }

    private fun updateRecyclerView(list: List<PopularMovie>) {
        (binding.recyclerView.adapter as PosterMovieAdapter).submitList(list)
    }

    private fun setUpRecyclerView() {
        with(binding) {
            recyclerView.adapter = PosterMovieAdapter()
        }
        updateSpanCount()
    }

    private fun updateSpanCount() {
        (binding.recyclerView.layoutManager as GridLayoutManager).spanCount = getDynamicSpanCount()
    }

    private fun getDynamicSpanCount(): Int {
        val widthInDP: Float
        val density = resources.displayMetrics.density
        val windowMetrics =
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(requireActivity())
        val currentBounds = windowMetrics.bounds
        val widthInPX = currentBounds.width()

        widthInDP = widthInPX / density

        return (widthInDP / 189).toInt()
    }
}