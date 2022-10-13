package com.soma.common_ui.presentation.highlight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.soma.common_ui.R
import com.soma.common_ui.databinding.ItemHighlightVideoBinding

class TestVideoAdapter(val dataSet: List<String>) :  RecyclerView.Adapter<TestVideoAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHighlightVideoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(videoId: String) {
            binding.highlightVideoYoutubePlayer.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHighlightVideoBinding>(LayoutInflater.from(parent.context),
            R.layout.item_highlight_video, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

}