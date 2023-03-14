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

import com.cara.graphicwalls.adapters.pyschedelicAdapter

import com.cara.graphicwalls.databinding.FragmentPsychedelicBinding

import com.cara.graphicwalls.imageviewmodelpac.psyViewModel



class PsychedelicFragment : Fragment()
{
    private lateinit var binding: FragmentPsychedelicBinding
    private lateinit var viewModel: psyViewModel
    private lateinit var imageAdapter: pyschedelicAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPsychedelicBinding.inflate(layoutInflater, container, false)
        imageAdapter = pyschedelicAdapter(mutableListOf())

        binding.revPsysch.apply {
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
            psyViewModel::class.java)
        viewModel.imageUrls.observe(viewLifecycleOwner) { urls ->
            imageAdapter.urls = urls.toMutableList()
            imageAdapter.notifyDataSetChanged()
        }
    }
}

