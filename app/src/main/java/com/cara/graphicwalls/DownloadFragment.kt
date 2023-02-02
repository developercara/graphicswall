package com.cara.graphicwalls


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cara.graphicwalls.databinding.FragmentDownloadBinding
import java.io.File
import java.util.jar.Manifest


class DownloadFragment : Fragment() {
    lateinit var binding: FragmentDownloadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)
//        return inflater.inflate(R.layout.fragment_download, container, false)



     val allFiles: Array<File>
        val imageList = arrayListOf<String>()

        val targetPath = Environment.getExternalStorageDirectory().absolutePath+"/Pictures/Amoled Wallpaper"
        val targetFile = File(targetPath)
         allFiles = targetFile.listFiles()!!

        for(data in allFiles){
            imageList.add(data.absolutePath)
        }

        for (i in imageList)
        {
            Log.e("@@@@", "onCreateView:$i")
        }

        binding.rcvCollection.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
       binding.rcvCollection.adapter = ImageAdapter(imageList)
       /*binding.rcvCollection.setOnClickListener {
            val intent = Intent(context, finalwallpaper::class.java)
           startActivity(intent)
        } */


        return binding.root
    }

}