package com.cara.graphicwalls
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cara.graphicwalls.Fragments.*
import com.cara.graphicwalls.adapters.Asthetic
import com.cara.graphicwalls.adapters.Mixed3d2dFragment
import com.cara.graphicwalls.adapters.UkiyoFragment

import com.cara.graphicwalls.databinding.FragmentCatoBinding


class catoFragment : Fragment() {
    lateinit var binding: FragmentCatoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatoBinding.inflate(layoutInflater,container,false)

        binding.Scat1.setOnClickListener {
            replaceFragment(CandyColorsFragment())
        }
        binding.Scat2.setOnClickListener {
            replaceFragment(NostaigaFragment())
        }
        binding.Scat3.setOnClickListener {
            replaceFragment(Mixed3d2dFragment())
        }
        binding.Scat4.setOnClickListener {
            replaceFragment(UkiyoFragment())
        }
        binding.Scat5.setOnClickListener {
            replaceFragment(D3dchracterFragment())
        }
        binding.Scat6.setOnClickListener {
            replaceFragment(MinimalFragment())
        }
        binding.Scat7.setOnClickListener {
            replaceFragment(astheticFragment())
        }
        binding.Scat8.setOnClickListener {
            replaceFragment(PsychedelicFragment())
        }

        // Inflate the layout for this fragment
        return binding.root
    }
    fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.replaceFragment, fragment)
        transaction?.commit()

    }



}