package com.cara.graphicwalls

import android.content.Intent
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
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hey Check out this Great app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
        binding.rateus.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Would you like rate this app:")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        binding.follow.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Follow me on Instagram:  https://www.instagram.com/boo.cara/")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }
        binding.youtube.setOnClickListener {
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Follow me on Youtube: https://www.youtube.com/channel/UCwTbpLNRCX1m2fr-w6hQ1NQ")
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }




        return binding.root
    }

}