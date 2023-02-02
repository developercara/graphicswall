package com.cara.graphicwalls.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cara.graphicwalls.ImageAdapter
import com.cara.graphicwalls.databinding.FragmentNostaigaBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_nostaiga.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception


class NostaigaFragment : Fragment() {
    lateinit var binding: FragmentNostaigaBinding
    var imageRef = Firebase.storage.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNostaigaBinding.inflate(layoutInflater, container, false)


        listFiles()
        return binding.root
    }

    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val images = imageRef.child("nostalgia/").listAll().await()
            val imageUrls = mutableListOf<String>()
            for (image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
                nos.visibility = View.VISIBLE
            }
            withContext(Dispatchers.Main) {
                val imageAdapter = ImageAdapter(imageUrls)
                nos.visibility = View.GONE
                rev_nostai.apply {
                    adapter = imageAdapter
                    layoutManager = GridLayoutManager(context, 2)
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {

            }
        }
    }
}