package com.example.apod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apod.api.GalleryItem
import com.example.apod.databinding.ListItemGalleryBinding

class ApodViewHolder(
    private val binding: ListItemGalleryBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {
        binding.itemImageView.load(galleryItem.url)
    }
}

class ApodListAdapter(
    private val galleryItems: List<GalleryItem>
): RecyclerView.Adapter<ApodViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return ApodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = galleryItems.size
}