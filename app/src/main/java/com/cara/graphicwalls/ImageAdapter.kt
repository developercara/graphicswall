package com.cara.graphicwalls

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_main.view.*

class ImageAdapter(
    val urls:List<String>
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){
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
        holder.itemView.setOnClickListener {
            val context=holder.itemView.context
            val intent = Intent( context, finalwallpaper::class.java)
            intent.putExtra("url",url)
            context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {

        return urls.size
    }
}