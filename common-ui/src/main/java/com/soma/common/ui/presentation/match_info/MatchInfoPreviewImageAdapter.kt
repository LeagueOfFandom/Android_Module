package com.soma.common.ui.presentation.match_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soma.common.ui.R
import com.soma.common.ui.databinding.ItemMatchPreviewImageObjectBinding

class MatchInfoPreviewImageAdapter(
    private val dataSet: List<String>
) : RecyclerView.Adapter<MatchInfoPreviewImageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemMatchPreviewImageObjectBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_preview_image_object, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.url = dataSet[position]
    }

    override fun getItemCount(): Int = dataSet.size
}