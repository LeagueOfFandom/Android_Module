package com.soma.common.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.soma.common.ui.R
import com.soma.common.ui.presentation.CommonMatchInfoVH
import com.soma.common.ui.presentation.match_info.MatchPreviewImageVH
import com.soma.common.ui.presentation.match_info.MatchPreviewTextVH
import com.soma.lof.core.model.entity.ViewType

object MatchInfoVHFactory {
    fun createViewHolder(parent: ViewGroup, viewType: Int): CommonMatchInfoVH {
        return when (viewType) {
            ViewType.MATCH_INFO_STRING_VIEW.ordinal -> MatchPreviewTextVH(getViewDataBinding(parent, R.layout.item_match_preview_text))
            ViewType.MATCH_INFO_IMAGE_VIEW.ordinal -> MatchPreviewImageVH(getViewDataBinding(parent, R.layout.item_match_preview_image))
            else -> MatchPreviewTextVH(getViewDataBinding(parent, R.layout.item_match_preview_image))
        }
    }

    private fun <T : ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }

}