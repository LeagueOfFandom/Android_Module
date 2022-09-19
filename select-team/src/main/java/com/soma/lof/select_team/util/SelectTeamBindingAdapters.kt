package com.soma.lof.select_team.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.common.data.entity.TeamInfo
import com.soma.lof.select_team.ui.SelectTeamListAdapter

@BindingAdapter("teamItems")
fun RecyclerView.bindTeamItems(teamList: List<TeamInfo>) {
    val boundAdapter = this.adapter
    if (boundAdapter is SelectTeamListAdapter) {
        boundAdapter.submitList(teamList)
    }
}