package com.cara.graphicwalls.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.cara.graphicwalls.databinding.FragmentMixed3d2dBinding
import com.cara.graphicwalls.imageviewmodelpac.mixedViewModel


class Mixed3d2dFragment : Fragment() {
    private lateinit var binding: FragmentMixed3d2dBinding
    private lateinit var viewModel: mixedViewModel
    private lateinit var imageAdapter:  mixed3d2dAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMixed3d2dBinding.inflate(layoutInflater, container, false)
        imageAdapter = mixed3d2dAdapter(mutableListOf())

        binding.revMixed3d2d.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        viewModel.loadMoreItems()
                    }
                }
            })
        }



        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this.requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            mixedViewModel::class.java)
        viewModel.imageUrls.observe(viewLifecycleOwner) { urls ->
            imageAdapter.urls = urls.toMutableList()
            imageAdapter.notifyDataSetChanged()
        }
    }
}


