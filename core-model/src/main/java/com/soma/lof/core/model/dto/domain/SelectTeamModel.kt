package com.soma.lof.core.model.dto.domain

import com.soma.lof.core.model.entity.LeagueTeamInfo
import com.soma.lof.core.model.entity.TeamInfo

data class SelectTeamModel(
    var leagueInfo: List<LeagueTeamInfo> = emptyList(),
    var leagueList: List<String> = emptyList(),
    var teamInfo: MutableList<TeamInfo> = mutableListOf()
)
