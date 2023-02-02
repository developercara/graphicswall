package com.cara.graphicwalls.Fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.cara.graphicwalls.ImageAdapter
import com.cara.graphicwalls.R
import com.cara.graphicwalls.databinding.FragmentAstheticBinding
import com.cara.graphicwalls.databinding.FragmentMinimalBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.fragment_asthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_minimal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class astheticFragment : Fragment() {
    lateinit var binding: FragmentAstheticBinding
    var imageRef = Firebase.storage.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAstheticBinding.inflate(layoutInflater,container,false)

        listFiles()
        return binding.root
    }
    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val images = imageRef.child("asthetics/").listAll().await()
            val imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
                aes.visibility = View.VISIBLE
            }
            withContext(Dispatchers.Main) {
                val imageAdapter = ImageAdapter(imageUrls)
                aes.visibility = View.GONE
                rev_asthetic.apply {
                    adapter = imageAdapter
                    layoutManager = GridLayoutManager(context, 2)
                }
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {

            }
        }
    }

}