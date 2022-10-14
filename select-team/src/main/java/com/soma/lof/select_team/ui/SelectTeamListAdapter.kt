package com.soma.lof.select_team.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.result.data
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.ItemSelectTeamBinding

class SelectTeamListAdapter(
    //private val whenItemClicked: () -> Unit,
    private val viewModel: SelectTeamViewModel,
    private val pos: Int
) : ListAdapter<TeamInfo, SelectTeamViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSelectTeamBinding>(layoutInflater,
            R.layout.item_select_team, parent, false)
            .apply {
                vm = viewModel
                leaguePos = pos
            }
        return SelectTeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectTeamViewHolder, position: Int) {
        holder.binding.team = getItem(position)
        holder.binding.teamPos = position

        val item = getItem(position)
        holder.binding.preferTeamCv.setOnClickListener {
            item.teamCheck = when (item.teamCheck) {
                true -> {
                    holder.binding.preferTeamCv.setUnchecked()
                    if (viewModel.selectTeamData.value.data!!.teamInfo.contains(item)) {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfoListResponse[position].teamCheck = false
                        viewModel.selectTeamData.value.data!!.teamInfo.remove(item)
                        viewModel.minusTeamCnt()
                    }
                    false
                }
                else -> {
                    if (!viewModel.selectTeamData.value.data!!.teamInfo.contains(item)) {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfoListResponse[position].teamCheck = true
                        viewModel.selectTeamData.value.data!!.teamInfo.add(item)
                        viewModel.plusTeamCnt()
                    }
                    holder.binding.preferTeamCv.setChecked()

                    true
                }
            }
        }
        holder.binding.executePendingBindings()
    }

    private fun MaterialCardView.setChecked() {
        this.strokeColor = getColor(context, R.color.main_color)
        this.strokeWidth = 10
    }

    private fun MaterialCardView.setUnchecked() {
        this.strokeColor = getColor(context, R.color.white)
        this.strokeWidth = 0
    }

}

class SelectTeamViewHolder(
    internal val binding: ItemSelectTeamBinding,
) : RecyclerView.ViewHolder(binding.root)

object DiffCallback : DiffUtil.ItemCallback<TeamInfo>() {
    override fun areItemsTheSame(oldItem: TeamInfo, newItem: TeamInfo): Boolean =
        oldItem.teamId == newItem.teamId

    override fun areContentsTheSame(oldItem: TeamInfo, newItem: TeamInfo): Boolean =
        oldItem == newItem
}