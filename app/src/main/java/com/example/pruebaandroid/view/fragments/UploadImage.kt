package com.example.pruebaandroid.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pruebaandroid.databinding.FragmentUploadImageBinding
import com.example.pruebaandroid.view.viewmodel.UploadImageViewModel

class UploadImage : Fragment() {


    private val viewModel: UploadImageViewModel by viewModels()
    private lateinit var binding: FragmentUploadImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}