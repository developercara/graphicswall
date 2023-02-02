package com.cara.graphicwalls.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cara.graphicwalls.R
import kotlinx.android.synthetic.main.item_main.view.*

class candyAdapter (val urls:List<String>) : RecyclerView.Adapter<candyAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = urls[position]
        Glide.with(holder.itemView).load(url).into(holder.itemView.id_image)
    }

    override fun getItemCount(): Int {
        return urls.size
    }
}
