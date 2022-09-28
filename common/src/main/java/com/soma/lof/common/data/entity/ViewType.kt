package com.soma.lof.common.data.entity

import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.common.ui.CommonVH
import com.soma.lof.common.ui.OneLineTextVH

enum class ViewType(viewType: String) {
    // COMMON
    ONE_LINE_TEXT_VIEW("ONE_LINE_TEXT_VIEW"),

    // HOME
    MATCH_LIVE_VIEW("MATCH_LIVE_VIEW"),
    TEXT_ARROW_VIEW("TEXT_ARROW_VIEW"),
    MATCH_RESULT_VIEW("MATCH_RESULT_VIEW"),
    MATCH_SCHEDULE_VIEW("MATCH_SCHEDULE_VIEW"),
    USER_TEAM_VIEW("USER_TEAM_VIEW"),
    COMMUNITY_VIEW("COMMUNITY_VIEW"),
    HIGHLIGHT_VIEW("HIGHLIGHT_VIEW"),
    ERROR_VIEW("ERROR_VIEW"),

    // MATCH_INFO
    MATCH_PREVIEW_TEXT_VIEW("MATCH_PREVIEW_TEXT_VIEW"),
    MATCH_PREVIEW_IMAGE_VIEW("MATCH_PREVIEW_IMAGE_VIEW"),

    // INFO
    INFO_DEFAULT_VIEW("INFO_DEFAULT_VIEW");
}