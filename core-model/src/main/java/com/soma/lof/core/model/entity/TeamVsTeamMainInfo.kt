package com.soma.lof.core.model.entity

data class TeamVsTeamMainInfo(
    val date: String,
    val time: String,
    val blueTeamAcronym: String,
    val redTeamAcronym: String,
    val blueTeamId: Long,
    val redTeamId: Long,
    val blueTeamImageUrl: String,
    val redTeamImageUrl: String,
    val blueTeamScore: Int,
    val redTeamScore: Int,
    val blueWin: Boolean,
    val redWin: Boolean
)
