package com.cara.graphicwalls.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cara.graphicwalls.R
import com.cara.graphicwalls.databinding.ItemMainBinding
import com.cara.graphicwalls.finalwallpaper


class mixed3d2dAdapter (var urls: MutableList<String>
) : RecyclerView.Adapter<mixed3d2dAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = urls[position]
        Glide.with(holder.itemView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.binding.idImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, finalwallpaper::class.java)
            intent.putExtra("url", url)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return urls.size
    }

    fun addImages(newUrls: List<String>) {
        val oldSize = urls.size
        urls.addAll(newUrls)
        notifyItemRangeInserted(oldSize, newUrls.size)
    }
}