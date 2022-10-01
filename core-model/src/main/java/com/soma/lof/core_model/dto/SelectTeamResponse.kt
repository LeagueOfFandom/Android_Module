package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.LeagueTeamInfo

data class SelectTeamResponse(
    val leagueInfo: List<LeagueTeamInfo>,
    val leagueList: List<String>,
)