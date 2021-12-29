package com.example.pruebaandroid.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.pruebaandroid.databinding.FragmentUploadImageBinding
import com.example.pruebaandroid.view.viewmodel.UploadImageViewModel
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class UploadImage : Fragment() {


    private val viewModel: UploadImageViewModel by viewModels()
    private lateinit var binding: FragmentUploadImageBinding
    private lateinit var imageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpButtons()
    }

    private fun setUpButtons() {
        binding.apply {
            selectImage.setOnClickListener { selectIMageFromGalery() }

            uploadImage.setOnClickListener { uploadImage() }
        }
    }

    private fun uploadImage() {
        val fireStorage = FirebaseStorage.getInstance()
        val storageReference = fireStorage.reference
        val id = Calendar.getInstance().timeInMillis
        val imageReference = storageReference.child("images/$id")
        imageReference.putFile(imageUri).addOnSuccessListener {
            binding.selectedImage.setImageURI(null)
            Toast.makeText(requireContext(), "Imagen subida exitosamente", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun selectIMageFromGalery() {
        val intent = Intent().apply {
            setType("image/*")
            setAction(Intent.ACTION_GET_CONTENT)
        }

        startActivityForResult(intent, 115)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 115) {
            data?.data?.apply {
                imageUri = this
                Glide.with(binding.selectedImage).load(imageUri).into(binding.selectedImage)
            }
        }
    }

}