package com.soma.lof.match.ui.match_info

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.common.ui.presentation.CommonListAdapter2
import com.soma.lof.core.model.entity.MatchPreviewCommonVO
import com.soma.lof.core.model.entity.SetInfoListData
import com.soma.lof.core.model.entity.TeamVsTeamSetInfo
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import com.soma.lof.domain.model.HomeModel

@BindingAdapter("matchPreviewItems")
fun RecyclerView.bindMatchPreviewItems(data: TeamVsTeamSetInfo?) {
    val boundAdapter = this.adapter
    if (boundAdapter is MatchInfoPreviewAdapter) {
        boundAdapter.submitList(data?.teamVsTeamSetInfo)
    }
}