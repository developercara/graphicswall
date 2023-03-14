package com.cara.graphicwalls.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cara.graphicwalls.adapters.d3dcharacterAdapter

import com.cara.graphicwalls.databinding.FragmentD3dchracterBinding

import com.cara.graphicwalls.imageviewmodelpac.d3dViewModel

class D3dchracterFragment : Fragment()  {
    private lateinit var binding: FragmentD3dchracterBinding
    private lateinit var viewModel: d3dViewModel
    private lateinit var imageAdapter: d3dcharacterAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentD3dchracterBinding.inflate(layoutInflater, container, false)
        imageAdapter = d3dcharacterAdapter(mutableListOf())

        binding.rev3dcharac.apply {
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
            d3dViewModel::class.java)
        viewModel.imageUrls.observe(viewLifecycleOwner) { urls ->
            imageAdapter.urls = urls.toMutableList()
            imageAdapter.notifyDataSetChanged()
        }
    }
}

