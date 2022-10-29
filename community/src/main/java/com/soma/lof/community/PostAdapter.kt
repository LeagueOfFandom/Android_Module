package com.soma.lof.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.community.databinding.ItemPostBinding
import com.soma.lof.core.model.entity.PostItem
import timber.log.Timber

class PostAdapter : ListAdapter<PostItem, PostAdapter.ViewHolder>(postAdapterDiffUtil){

    inner class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var photoAdapter: PhotoAdapter

        fun bind(item: PostItem) {
            photoAdapter = PhotoAdapter()
            binding.also {
                it.item = item
                it.photoAdapter = photoAdapter
            }
            binding.itemPostPhotoRv.addItemDecoration(PhotoItemDecoration())
            photoAdapter.submitList(item.postPhotoList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val postAdapterDiffUtil = object : DiffUtil.ItemCallback<PostItem>() {
            override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
                return oldItem.postId == newItem.postId
            }

            override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}