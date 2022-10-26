package com.soma.lof.core.model.dto

import com.soma.lof.core.model.entity.LeagueTeamInfo

data class LeagueTeamResponse(
    val leagueInfoList: List<LeagueTeamInfo>,
    val leagueNameList: List<String>,
)