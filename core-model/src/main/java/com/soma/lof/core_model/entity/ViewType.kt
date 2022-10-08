package com.soma.lof.core_model.entity

enum class ViewType(viewType: String) {
    // COMMON
    ONE_LINE_TEXT_VIEW("ONE_LINE_TEXT_VIEW"),

    // HOME
    LIVE_VIEW("LIVE_VIEW"),
    HOME_MATCH_TITLE_VIEW("HOME_MATCH_TITLE_VIEW"),
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
    INFO_EVENT_VIEW("INFO_EVENT_VIEW"),
    INFO_LEAGUE_VIEW("INFO_LEAGUE_VIEW"),
    INFO_HIGHLIGHT_VIEW("INFO_HIGHLIGHT_VIEW"),
    INFO_COMMENT_VIEW("INFO_COMMENT_VIEW"),
    INFO_POST_LIKE_VIEW("INFO_POST_LIKE_VIEW"),
    INFO_POST_SUCCESS_VIEW("INFO_POST_SUCCESS_VIEW"),

}