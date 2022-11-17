package com.soma.lof.select_team.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.soma.lof.core.model.entity.TeamInfo
import com.soma.lof.core.result.data
import com.soma.lof.select_team.R
import com.soma.lof.select_team.databinding.ItemSelectTeamBinding
import timber.log.Timber

class SelectTeamListAdapter(
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
                    if (viewModel.userTeamInfoList.contains(item)) {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfoList[position].teamCheck = false
                        Timber.tag("check@@@").d("remove ${item.teamId}")
                        viewModel.removeTeam(item)
                        viewModel.minusTeamCnt()
                    }
                    false
                }
                else -> {
                    if (!viewModel.userTeamInfoList.contains(item)) {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfoList[position].teamCheck = true
                        Timber.tag("check@@@").d("add ${item.teamId}")
                        viewModel.addTeam(item)
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