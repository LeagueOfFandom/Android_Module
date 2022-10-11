package com.soma.lof.core_model.entity

data class TeamInfo(
    val league: String = "",
    var teamCheck: Boolean = false,
    val teamId: Long = 0L,
    val teamImg: String = "",
    val teamName: String = "",
)