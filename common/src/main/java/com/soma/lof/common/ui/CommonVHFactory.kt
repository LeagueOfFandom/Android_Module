package com.soma.lof.common.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.soma.lof.common.R
import com.soma.lof.common.data.entity.ViewType

object CommonVHFactory {
    fun createViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return when(viewType) {
            ViewType.MATCH_LIVE_VIEW.ordinal -> MatchLiveVH(getViewDataBinding(parent, R.layout.item_match_live))
            ViewType.MATCH_RESULT_VIEW.ordinal -> MatchResultVH(getViewDataBinding(parent, R.layout.item_match_result))
            ViewType.MATCH_SCHEDULE_VIEW.ordinal -> MatchScheduleVH(getViewDataBinding(parent, R.layout.item_match_schedule))
            ViewType.TEXT_ARROW_VIEW.ordinal -> TextArrowVH(getViewDataBinding(parent, R.layout.item_text_arrow))
            ViewType.HIGHLIGHT_VIEW.ordinal -> HighLightVH(getViewDataBinding(parent, R.layout.item_highlight_view))
            else -> MatchScheduleVH(getViewDataBinding(parent, R.layout.item_match_schedule))
        }
    }

    private fun <T: ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }
}