package com.soma.lof.core_model.dto.domain

import com.soma.lof.core_model.entity.LeagueTeamInfo
import com.soma.lof.core_model.entity.TeamInfo

data class SelectTeamModel(
    var leagueInfo: List<LeagueTeamInfo> = emptyList(),
    var leagueList: List<String> = emptyList(),
    var teamInfo: List<TeamInfo> = emptyList()
)
