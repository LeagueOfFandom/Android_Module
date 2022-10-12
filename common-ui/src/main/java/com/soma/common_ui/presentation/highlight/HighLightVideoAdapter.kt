package com.soma.common_ui.presentation.highlight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.soma.common_ui.R
import com.soma.common_ui.databinding.ItemHighlightVideoBinding

class HighLightVideoAdapter : ListAdapter<String, HighLightVideoAdapter.HighLightVideoViewHolder>(
    HighLightVideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighLightVideoViewHolder {
        val binding = DataBindingUtil.inflate<ItemHighlightVideoBinding>(LayoutInflater.from(parent.context),
            R.layout.item_highlight_video, parent, false)
        return HighLightVideoViewHolder(binding)
    }

    inner class HighLightVideoViewHolder(private val binding: ItemHighlightVideoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(videoId: String) {
            binding.highlightVideoYoutubePlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        }
    }

    override fun onBindViewHolder(holder: HighLightVideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class HighLightVideoDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}