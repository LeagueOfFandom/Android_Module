package com.soma.lof.select_team.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.common.data.entity.TeamInfo
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.ItemSelectTeamBinding

class SelectTeamListAdapter(
    //private val whenItemClicked: () -> Unit,
    private val viewModel: SelectTeamViewModel
) : ListAdapter<TeamInfo, SelectTeamListAdapter.SelectTeamViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSelectTeamBinding>(layoutInflater,
            R.layout.item_select_team, parent, false)
        return SelectTeamViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener

//                whenItemClicked(
//                    getItem(position),
//                    Pair()
//                )
            }
        }
    }

    override fun onBindViewHolder(holder: SelectTeamViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SelectTeamViewHolder (
        private val binding: ItemSelectTeamBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TeamInfo) {
            binding.apply {
                team = item
                executePendingBindings()
            }

            when (item.teamCheck) {
                true -> {
                    binding.preferTeamCv.strokeColor = getColor(itemView.context,R.color.main_color)
                    binding.preferTeamCv.strokeWidth = 10
                }
                else -> {
                    binding.preferTeamCv.strokeColor = getColor(itemView.context,R.color.white)
                    binding.preferTeamCv.strokeWidth = 0
                }
            }

            binding.preferTeamCv.setOnClickListener {
                item.teamCheck = when (item.teamCheck) {
                    true -> {
                        binding.preferTeamCv.strokeColor = getColor(itemView.context, R.color.white)
                        binding.preferTeamCv.strokeWidth = 0
                        viewModel.minusTeamCnt()
                        false
                    }
                    else -> {
                        binding.preferTeamCv.strokeColor = getColor(itemView.context, R.color.main_color)
                        binding.preferTeamCv.strokeWidth = 10
                        viewModel.plusTeamCnt()
                        true
                    }
                }
            }
        }
    }

    private fun getColor(context: Context, color: Int): Int = ResourcesCompat.getColor(context.resources, color, null)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TeamInfo>() {
            override fun areItemsTheSame(oldItem: TeamInfo, newItem: TeamInfo): Boolean =
                oldItem.teamId == newItem.teamId

            override fun areContentsTheSame(oldItem: TeamInfo, newItem: TeamInfo): Boolean =
                oldItem == newItem
        }
    }
}