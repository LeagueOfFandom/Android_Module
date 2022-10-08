package com.soma.lof.core_model.util

import com.soma.lof.core_model.entity.*
import javax.swing.text.View

object ViewObjectFactory {
    fun createViewObject(viewType: Int, data: CommonViewObject): ViewObject {
        return when (viewType) {
            ViewType.LIVE_VIEW.ordinal, ViewType.MATCH_RESULT_VIEW.ordinal, ViewType.MATCH_SCHEDULE_VIEW.ordinal ->
                MatchViewObject(data.matchId,
                    data.homeName,
                    data.homeImg,
                    data.awayName,
                    data.awayImg,
                    data.date,
                    data.time,
                    data.league,
                    data.isAlarm,
                    data.homeScore,
                    data.awayScore)
            ViewType.TEXT_ARROW_VIEW.ordinal -> TextArrowViewObject(
                data.text)
            ViewType.COMMUNITY_VIEW.ordinal -> CommunityViewObject(
                data.nickname,
                data.profileImg,
                data.time,
                data.content)
            ViewType.HIGHLIGHT_VIEW.ordinal -> HighLightViewObject(
                data.videoList)
            ViewType.MATCH_PREVIEW_TEXT_VIEW.ordinal -> MatchPreviewImageViewObject(
                data.text,
                data.blueImgList,
                data.redImgList)
            ViewType.MATCH_PREVIEW_IMAGE_VIEW.ordinal -> MatchPreviewTextViewObject(
                data.text,
                data.blueData,
                data.redData)
            ViewType.ONE_LINE_TEXT_VIEW.ordinal -> OneLineTextViewObject(
                data.text)

            ViewType.INFO_EVENT_VIEW.ordinal, ViewType.INFO_COMMENT_VIEW.ordinal, ViewType.INFO_LEAGUE_VIEW.ordinal,
            ViewType.INFO_POST_SUCCESS_VIEW.ordinal, ViewType.INFO_POST_LIKE_VIEW.ordinal, ViewType.INFO_HIGHLIGHT_VIEW.ordinal->
                InfoViewObject(
                    data.infoTitle,
                    data.infoContent,
                    data.infoIsCheck,
                    data.infoTimeCompare,
                    data.infoDateTime
                )
            else -> OneLineTextViewObject(data.text)
        }
    }
}