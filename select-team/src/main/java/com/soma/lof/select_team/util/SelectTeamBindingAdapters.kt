package com.soma.lof.select_team.util

import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.soma.lof.core.model.entity.TeamInfo
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import com.soma.lof.core.result.successOrNull
import com.soma.lof.domain.model.SelectTeamModel
import com.soma.lof.select_team.R
import com.soma.lof.select_team.ui.SelectTeamListAdapter

@BindingAdapter("teamItems", "position")
fun RecyclerView.bindTeamItems(state: UiState<SelectTeamModel>, pos: Int) {
    val boundAdapter = this.adapter
    if (boundAdapter is SelectTeamListAdapter && state.successOrNull() != null)
        if (state.data != null) {
            boundAdapter.submitList(state.data!!.leagueInfo[pos].teamInfoList)
        }
}

@BindingAdapter("leagueNote", "position")
fun TextView.text(state: UiState<SelectTeamModel>, pos: Int) {
    if (state.successOrNull() != null) {
        this.text = state.data!!.leagueInfo[pos].note
    }
}

@BindingAdapter("teamSelectStrokeColor")
fun MaterialCardView.strokeColor(teamInfo: TeamInfo) {
    if (teamInfo.teamCheck) {
        this.strokeColor = getColor(context, R.color.main_color)
    } else {
        this.strokeColor = getColor(context, R.color.white)
    }
}

@BindingAdapter("teamSelectStrokeWidth")
fun MaterialCardView.strokeWidth(teamInfo: TeamInfo) {
    if (teamInfo.teamCheck) {
        this.strokeWidth = 10
    } else {
        this.strokeColor = 0
    }
}