package com.soma.lof.match.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.match.R
import com.soma.lof.match.databinding.ItemMatchDateGameScheduleBinding
import com.soma.lof.match.model.entity.MatchData

class MatchDateGameAdapter(
    private val viewModel: MatchViewModel
) : ListAdapter<MatchData, MatchDateGameAdapter.MatchDateGameViewHolder>(diffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchDateGameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMatchDateGameScheduleBinding>(layoutInflater,
            R.layout.item_match_date_game_schedule, parent, false)

        return MatchDateGameViewHolder(binding).apply {
            binding.root.setOnClickListener {

            }
        }
    }

    override fun onBindViewHolder(holder: MatchDateGameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MatchDateGameViewHolder(
        private val binding: ItemMatchDateGameScheduleBinding
    )  : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MatchData) {
            binding.apply {
                data = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MatchData>() {
            override fun areItemsTheSame(oldItem: MatchData, newItem: MatchData): Boolean =
                oldItem.matchId == newItem.matchId

            override fun areContentsTheSame(oldItem: MatchData, newItem: MatchData): Boolean =
                oldItem == newItem
        }
    }
}