package com.cara.graphicwalls.Fragments

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cara.graphicwalls.adapters.candyAdapter
import com.cara.graphicwalls.databinding.FragmentCandyColorsBinding
import com.cara.graphicwalls.imageviewmodelpac.candyViewModel

class CandyColorsFragment : Fragment() {
    private lateinit var binding: FragmentCandyColorsBinding
    private lateinit var viewModel: candyViewModel
    private lateinit var imageAdapter: candyAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCandyColorsBinding.inflate(layoutInflater, container, false)
        imageAdapter = candyAdapter(mutableListOf())

        binding.revCandy.apply {
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
            candyViewModel::class.java)
        viewModel.imageUrls.observe(viewLifecycleOwner) { urls ->
            imageAdapter.urls = urls.toMutableList()
            imageAdapter.notifyDataSetChanged()
        }
    }
}


