package com.soma.lof.core_model.entity

data class MatchViewObject(
    val matchId: Long,
    val homeName: String,
    val homeImg: String,
    val awayName: String,
    val awayImg: String,
    val date: String?,
    val time: String,
    val league: String,
    val isAlarm: Boolean,
    val homeScore: Int,
    val awayScore: Int,
    var isHide: Boolean = true
) : ViewObject()
