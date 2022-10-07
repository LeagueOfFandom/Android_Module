package com.soma.lof.core_model.entity

data class CommonViewObject(

    // Text_Arrow
    val text: String?,

    // Community
    val nickname: String?,
    val profileImg: String?,
    val time: String?,
    val content: String?,

    // Match
    val matchId: Long?,
    val homeName: String?,
    val homeImg: String?,
    val awayName: String?,
    val awayImg: String?,
    val date: String?,
    val league: String?,
    val isAlarm: Boolean?,
    val homeScore: Int?,
    val awayScore: Int?,
    val status: String?,

    // Highlight
    val videoList: List<String>?,

    // PreviewImage
    val blueImgList: List<String>?,
    val redImgList: List<String>?,

    // PreviewText
    val blueData: String?,
    val redData: String?,

    // Info
    val isCheck: Boolean?,



)