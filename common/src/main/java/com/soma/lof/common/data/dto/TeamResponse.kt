package com.soma.lof.common.data.dto

import com.soma.lof.common.data.entity.LeagueTeamInfo

data class TeamResponse(
    val leagueInfo: List<LeagueTeamInfo>,
    val leagueList: List<String>,
)