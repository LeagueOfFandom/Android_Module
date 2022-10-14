package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.LeagueTeamInfo

data class LeagueTeamResponse(
    val leagueInfoListListResponse: List<LeagueTeamInfo>,
    val leagueNameList: List<String>,
)