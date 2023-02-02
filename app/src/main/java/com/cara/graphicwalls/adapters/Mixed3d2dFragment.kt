package com.cara.graphicwalls.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cara.graphicwalls.ImageAdapter
import com.cara.graphicwalls.R
import com.cara.graphicwalls.databinding.FragmentCandyColorsBinding
import com.cara.graphicwalls.databinding.FragmentMixed3d2dBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_candy_colors.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mixed3d2d.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class Mixed3d2dFragment : Fragment() {
    lateinit var binding: FragmentMixed3d2dBinding
    var imageRef = Firebase.storage.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMixed3d2dBinding.inflate(layoutInflater,container,false)


        listFiles()
        return binding.root
    }
    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val images = imageRef.child("/mixed3d2d/").listAll().await()
            val imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
                mix.visibility = View.VISIBLE
            }
            withContext(Dispatchers.Main) {
                val imageAdapter = ImageAdapter(imageUrls)
                mix.visibility = View.GONE
                rev_mixed3d2d.apply {
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