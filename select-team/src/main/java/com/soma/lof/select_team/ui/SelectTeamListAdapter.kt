package com.soma.lof.select_team.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.common_ui.generated.callback.OnClickListener
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

        /* TODO API 정리 후 추후 수정 예정 */
        holder.binding.preferTeamCv.setOnClickListener {
            getItem(position).teamCheck = when (getItem(position).teamCheck) {
                true -> {
                    holder.binding.preferTeamCv.strokeColor = getColor(holder.itemView.context, R.color.white)
                    holder.binding.preferTeamCv.strokeWidth = 0
                    if (viewModel.selectTeamData.value.data!!.teamInfo.contains(getItem(position))) {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfo[position].teamCheck = false
                        viewModel.selectTeamData.value.data!!.teamInfo.remove(getItem(position))
                    } else {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfo[position].teamCheck = true
                        viewModel.selectTeamData.value.data!!.teamInfo.add(getItem(position))
                    }

                    Log.d("OnClick", "onClick: ${viewModel.selectTeamData.value.data!!.teamInfo.size}")
                    viewModel.minusTeamCnt()
                    false
                }
                else -> {
                    if (viewModel.selectTeamData.value.data!!.teamInfo.contains(getItem(position))) {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfo[position].teamCheck = false
                        viewModel.selectTeamData.value.data!!.teamInfo.remove(getItem(position))
                    } else {
                        viewModel.selectTeamData.value.data!!.leagueInfo[pos].teamInfo[position].teamCheck = true
                        viewModel.selectTeamData.value.data!!.teamInfo.add(getItem(position))
                    }
                    Log.d("OnClick", "onClick: ${viewModel.selectTeamData.value.data!!.teamInfo.size}")
                    holder.binding.preferTeamCv.strokeColor =
                        getColor(holder.itemView.context, R.color.main_color)
                    holder.binding.preferTeamCv.strokeWidth = 10
                    viewModel.plusTeamCnt()
                    true
                }
            }
        }
        holder.binding.executePendingBindings()
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