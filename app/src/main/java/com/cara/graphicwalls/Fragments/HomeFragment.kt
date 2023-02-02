package com.cara.graphicwalls.Fragments

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cara.graphicwalls.ImageAdapter
import com.cara.graphicwalls.R
import com.cara.graphicwalls.databinding.FragmentHomeBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.progressbar.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    var imageRef = Firebase.storage.reference
    private lateinit var mProgressDialog: Dialog
//    private lateinit var db: FirebaseFirestore






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

//        val settings = firestoreSettings {
//            isPersistenceEnabled = true
//        }
//        db.firestoreSettings = settings

       //showProgessDialog("Loading..")

        listFiles()


        return binding.root
    }
    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {

        try {

            val images = imageRef.child("images/").listAll().await()
            val imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
                kkkk.visibility = View.VISIBLE




            }
            withContext(Dispatchers.Main) {
                val imageAdapter = ImageAdapter(imageUrls)
                  // hideProgessDialog()
                   kkkk.visibility = View.GONE
                rev_main.apply {
                    adapter = imageAdapter
                    layoutManager = GridLayoutManager(context, 2)
                }
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {

            }
        }
    }
    fun showProgessDialog(text:String){
        mProgressDialog = Dialog(requireContext())
        /*set the screen content from a layout resource
        the resource will be inflated, adding all top-level views to screen*/
        mProgressDialog.setContentView(R.layout.progressbar)
        mProgressDialog.tv_progessBar.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(true)

        mProgressDialog.show()
    }



    fun hideProgessDialog(){
        mProgressDialog.dismiss()
    }

}
