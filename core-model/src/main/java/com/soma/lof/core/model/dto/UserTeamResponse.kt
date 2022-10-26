package com.soma.lof.core.model.dto

data class UserTeamResponse(
    val teamId: Long,
    val league: String,
    val teamImg: String,
    val teamName: String,
    val teamCheck: Boolean,
)