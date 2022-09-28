package com.soma.lof.core_model.entity

data class TeamInfo(
    val league: String,
    var teamCheck: Boolean,
    val teamId: Long,
    val teamImg: String,
    val teamName: String
)