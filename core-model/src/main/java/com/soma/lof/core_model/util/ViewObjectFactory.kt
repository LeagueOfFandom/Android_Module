package com.soma.lof.core_model.util

import com.soma.lof.core_model.entity.*

object ViewObjectFactory {
    fun createViewObject(viewType: Int, data: CommonVO): ViewObject {
        return when (viewType) {
            ViewType.LIVE_VIEW.ordinal, ViewType.MATCH_RESULT_VIEW.ordinal, ViewType.MATCH_SCHEDULE_VIEW.ordinal ->
                MatchVO(
                    data.matchId,
                    data.homeName,
                    data.homeImg,
                    data.awayName,
                    data.awayImg,
                    data.date,
                    data.time,
                    data.league,
                    data.isAlarm,
                    data.homeScore,
                    data.awayScore
                )
            ViewType.TEXT_ARROW_VIEW.ordinal -> TextVO(
                data.text
            )
            ViewType.COMMUNITY_VIEW.ordinal -> CommunityVO(
                data.nickname,
                data.profileImg,
                data.time,
                data.content
            )
            ViewType.HIGHLIGHT_VIEW.ordinal -> HighLightVO(
                data.videoList
            )
            ViewType.MATCH_PREVIEW_TEXT_VIEW.ordinal -> MatchPreviewImageVO(
                data.text,
                data.blueImgList,
                data.redImgList
            )
            ViewType.MATCH_PREVIEW_IMAGE_VIEW.ordinal -> MatchPreviewTextVO(
                data.text,
                data.blueData,
                data.redData
            )
            ViewType.ONE_LINE_TEXT_VIEW.ordinal -> TextVO(
                data.text
            )

            // HOME_FRAGMENT, MATCH_FRAGMENT
            ViewType.MATCH_SCHEDULE_DATE_LINE.ordinal, ViewType.MATCH_TODAY_DATE_LINE.ordinal, ViewType.MATCH_RESULT_DATE_LINE.ordinal
            -> TextVO(
                data.text
            )

            // INFO_FRAGMENT
            ViewType.INFO_EVENT_VIEW.ordinal, ViewType.INFO_COMMENT_VIEW.ordinal, ViewType.INFO_LEAGUE_VIEW.ordinal,
            ViewType.INFO_POST_SUCCESS_VIEW.ordinal, ViewType.INFO_POST_LIKE_VIEW.ordinal, ViewType.INFO_HIGHLIGHT_VIEW.ordinal ->
                InfoVO(
                    data.infoTitle,
                    data.infoContent,
                    data.infoIsCheck,
                    data.infoTimeCompare,
                    data.infoDateTime
                )
            else -> TextVO(data.text)
        }
    }
}