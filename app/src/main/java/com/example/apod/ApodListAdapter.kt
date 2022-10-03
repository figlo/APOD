package com.example.apod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apod.databinding.ListItemGalleryBinding

class ApodViewHolder(
    private val binding: ListItemGalleryBinding,
    private val itemWidth: Int,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(apodDomainModel: ApodDomainModel, onItemClicked: (apodId: Long) -> Unit) {
        with(binding) {
            itemImageView.layoutParams.width = itemWidth
            itemImageView.layoutParams.height = itemWidth
            itemImageView.load(apodDomainModel.url)
            itemTextView.text = ("${apodDomainModel.title} (${apodDomainModel.date})")      // TODO
            root.setOnClickListener { onItemClicked(apodDomainModel.id) }
        }
    }
}

class ApodListAdapter(
    private val apodApiModels: List<ApodDomainModel>,
    private val itemWidth: Int,
    private val onItemClicked: (apodId: Long) -> Unit,
) : RecyclerView.Adapter<ApodViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return ApodViewHolder(binding, itemWidth)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val item = apodApiModels[position]
        holder.bind(item, onItemClicked)
    }

    override fun getItemCount() = apodApiModels.size
}