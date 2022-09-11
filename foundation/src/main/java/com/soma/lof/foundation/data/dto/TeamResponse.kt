package com.soma.lof.foundation.data.dto

import com.soma.lof.foundation.data.entity.LeagueTeamInfo

data class TeamResponse(
    val leagueInfo: List<LeagueTeamInfo>,
    val leagueList: List<String>,
)