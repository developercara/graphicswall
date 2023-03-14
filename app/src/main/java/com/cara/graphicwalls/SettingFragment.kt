package com.cara.graphicwalls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cara.graphicwalls.databinding.FragmentMinimalBinding
import com.cara.graphicwalls.databinding.FragmentSettingBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater,container,false)


        binding.sharethisapp.setOnClickListener {
            shareApp()
        }
        binding.rateus.setOnClickListener {
            rateApp()
        }

        binding.follow.setOnClickListener {
            val uri = Uri.parse("https://www.instagram.com/boo.cara/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.youtube.setOnClickListener {
            val uri = Uri.parse("https://www.youtube.com/channel/UCwTbpLNRCX1m2fr-w6hQ1NQ")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.github.setOnClickListener {
            val uri = Uri.parse("https://github.com/developercara")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }






        return binding.root
    }
    private fun rateApp() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.cara.graphicwalls")))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.cara.graphicwalls")))
        }
    }

    private fun shareApp() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        val shareBody = "Check out this cool app!\nhttps://play.google.com/store/apps/details?id=com.cara.graphicwalls"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "My App")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share via"))
    }


}