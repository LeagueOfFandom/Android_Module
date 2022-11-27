package com.soma.lof.match.ui.match_info

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.core.model.entity.TeamVsTeamSetInfo

@BindingAdapter("matchPreviewItems")
fun RecyclerView.bindMatchPreviewItems(data: TeamVsTeamSetInfo?) {
    val boundAdapter = this.adapter
    if (boundAdapter is MatchInfoPreviewAdapter) {
        boundAdapter.submitList(data?.teamVsTeamSetInfo)
    }
}