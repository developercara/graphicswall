package com.cara.graphicwalls



import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cara.graphicwalls.databinding.FragmentDownloadBinding
import java.io.File


class DownloadFragment : Fragment() {
    lateinit var binding: FragmentDownloadBinding
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)
//        return inflater.inflate(R.layout.fragment_download, container, false)
        imageAdapter = ImageAdapter(mutableListOf())

        binding.rcvCollection.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get list of downloaded image file paths
        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Amoled Wallpaper")
        val imageFiles = directory.listFiles()?.filter { it.extension == "jpg" }

        // Map file paths to URLs and update adapter
        imageAdapter.urls = imageFiles?.map { it.toURI().toString() }?.toMutableList() ?: mutableListOf()
        imageAdapter.notifyDataSetChanged()
    }

}