package com.cara.graphicwalls

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.cara.graphicwalls.Fragments.HomeFragment
import com.cara.graphicwalls.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var imageRef = Firebase.storage.reference
    private lateinit var db:FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //status bar
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.status)


        replaceFragment(HomeFragment())
        binding.navigationbtn.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.cato -> replaceFragment(catoFragment())
                R.id.download -> replaceFragment(DownloadFragment())
                R.id.setting -> replaceFragment(SettingFragment())
                else->{

                }
            }
            true
        }


    }
    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.replaceFragment, fragment)
        transaction.commit()

    }

}