package com.soma.lof.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.community.databinding.ItemPhotoBinding
import com.soma.lof.core.model.entity.PhotoItem
import timber.log.Timber

class PhotoAdapter : ListAdapter<PhotoItem, PhotoAdapter.ViewHolder>(photoAdapterDiffUtil) {

    inner class ViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoItem) {
            binding.item = item
            Timber.d("photo item: ${item}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        return ViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val photoAdapterDiffUtil = object : DiffUtil.ItemCallback<PhotoItem>() {
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
                return oldItem.photoId == newItem.photoId
            }

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}