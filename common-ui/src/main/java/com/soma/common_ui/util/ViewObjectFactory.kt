import com.soma.common_ui.presentation.TextArrowViewObject
import com.soma.lof.core_model.entity.*


object ViewObjectFactory {
    fun createViewObject(viewType: Int, data: CommonViewObject) : ViewObject {
        return when (viewType) {
            ViewType.MATCH_LIVE_VIEW.ordinal, ViewType.MATCH_RESULT_VIEW.ordinal, ViewType.MATCH_SCHEDULE_VIEW.ordinal ->
                MatchViewObject(data.matchId!!,
                    data.homeName!!,
                    data.homeImg!!,
                    data.awayName!!,
                    data.awayImg!!,
                    data.date!!,
                    data.matchTime!!,
                    data.league!!,
                    data.isAlarm!!,
                    data.homeScore!!,
                    data.awayScore!!,
                    data.isHide!!)
           ViewType.TEXT_ARROW_VIEW.ordinal -> TextArrowViewObject(
                data.text!!)
           ViewType.COMMUNITY_VIEW.ordinal -> CommunityViewObject(
               data.nickname!!,
               data.profileImg!!,
               data.time!!,
               data.content!!)
           ViewType.HIGHLIGHT_VIEW.ordinal -> HighLightViewObject(
               data.videoList!!)
           ViewType.MATCH_PREVIEW_TEXT_VIEW.ordinal -> MatchPreviewImageViewObject(
               data.text!!,
               data.blueImgList!!,
               data.redImgList!!)
           ViewType.MATCH_PREVIEW_IMAGE_VIEW.ordinal -> MatchPreviewTextViewObject(
               data.text!!,
               data.blueData!!,
               data.redData!!)
           ViewType.ONE_LINE_TEXT_VIEW.ordinal -> OneLineTextViewObject(
               data.text!!)
           ViewType.INFO_DEFAULT_VIEW.ordinal -> InfoDefaultViewObject(
               data.text!!,
               data.isCheck!!)
            else -> OneLineTextViewObject(data.text!!)
        }
    }
}