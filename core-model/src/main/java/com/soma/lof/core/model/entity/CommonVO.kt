package com.soma.lof.core.model.entity

/**
 * This [CommonVO] is for obtaining each ViewType ViewHolder Data
 */
data class CommonVO(

    // Text_Arrow
    val text: String = "",

    // Community
    val nickname: String = "",
    val profileImg: String = "",
    val time: String = "",
    val content: String = "",

    // Match
    val matchId: Long = 0L,
    val homeName: String = "",
    val homeImg: String = "",
    val awayName: String = "",
    val awayImg: String = "",
    val date: String = "",
    val league: String = "",
    val isAlarm: Boolean = false,
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val status: String = "",

    // Highlight
    val videoList: List<String> = emptyList(),

    // PreviewImage
    val blueImgList: List<String> = emptyList(),
    val redImgList: List<String> = emptyList(),

    // PreviewText
    val blueData: String = "",
    val redData: String = "",

    // Info
    val infoTitle: String = "",
    val infoContent: String = "",
    val infoIsCheck: Boolean = false,
    val infoTimeCompare: String = "",
    val infoDateTime: String = "",
)