package com.soma.common.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.soma.common.ui.R
import com.soma.common.ui.presentation.*
import com.soma.common.ui.presentation.highlight.HighLightVH
import com.soma.common.ui.presentation.info.*
import com.soma.common.ui.presentation.match_info.MatchPreviewImageVH
import com.soma.common.ui.presentation.match_info.MatchPreviewTextVH
import com.soma.common.ui.presentation.match_up.*
import com.soma.lof.core.model.entity.ViewType

object CommonVHFactory {
    fun createViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return when(viewType) {
            ViewType.LIVE_VIEW.ordinal -> MatchLiveVH(getViewDataBinding(parent, R.layout.item_match_live))
            ViewType.HOME_MATCH_TITLE_VIEW.ordinal -> HomeMatchTitleLineVH(getViewDataBinding(parent, R.layout.item_home_match_title_line))
            ViewType.MATCH_RESULT_VIEW.ordinal -> MatchResultVH(getViewDataBinding(parent, R.layout.item_match_result))
            ViewType.MATCH_SCHEDULE_VIEW.ordinal -> MatchScheduleVH(getViewDataBinding(parent, R.layout.item_match_schedule))
            ViewType.TEXT_ARROW_VIEW.ordinal -> TextArrowVH(getViewDataBinding(parent, R.layout.item_text_arrow))
            ViewType.COMMUNITY_VIEW.ordinal -> CommunityVH(getViewDataBinding(parent, R.layout.item_community))
            ViewType.HIGHLIGHT_VIEW.ordinal -> HighLightVH(getViewDataBinding(parent, R.layout.item_highlight_view))

            // MATCH_UP
            ViewType.MATCH_SCHEDULE_DATE_LINE.ordinal -> MatchScheduleDateLineVH(getViewDataBinding(parent, R.layout.item_match_schuedule_date_line))
            ViewType.MATCH_RESULT_DATE_LINE.ordinal -> MatchResultDateLineVH(getViewDataBinding(parent, R.layout.item_match_result_date_line))
            ViewType.MATCH_TODAY_DATE_LINE.ordinal -> MatchTodayDateLineVH(getViewDataBinding(parent, R.layout.item_match_today_date_line))

            ViewType.MATCH_PREVIEW_TEXT_VIEW.ordinal -> MatchPreviewTextVH(getViewDataBinding(parent, R.layout.item_match_preview_text))
            ViewType.MATCH_PREVIEW_IMAGE_VIEW.ordinal -> MatchPreviewImageVH(getViewDataBinding(parent, R.layout.item_match_preview_image))

            ViewType.ONE_LINE_TEXT_VIEW.ordinal -> OneLineTextVH(getViewDataBinding(parent, R.layout.item_one_line_text))

            /* Info Fragment UI */
            ViewType.INFO_EVENT_VIEW.ordinal -> InfoEventVH(getViewDataBinding(parent, R.layout.item_info_event))
            ViewType.INFO_LEAGUE_VIEW.ordinal -> InfoLeagueVH(getViewDataBinding(parent, R.layout.item_info_league))
            ViewType.INFO_HIGHLIGHT_VIEW.ordinal -> InfoHighlightVH(getViewDataBinding(parent, R.layout.item_info_highlight))
            ViewType.INFO_COMMENT_VIEW.ordinal -> InfoCommentVH(getViewDataBinding(parent, R.layout.item_info_comment))
            ViewType.INFO_POST_LIKE_VIEW.ordinal -> InfoPostLikeVH(getViewDataBinding(parent, R.layout.item_info_post_like))
            ViewType.INFO_POST_SUCCESS_VIEW.ordinal -> InfoPostSuccessVH(getViewDataBinding(parent, R.layout.item_info_post_success))

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