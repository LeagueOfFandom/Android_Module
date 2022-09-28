package com.soma.lof.select_team.model

import com.soma.lof.core_model.entity.TeamInfo

data class FakeLeagueTeamList(
    val note: String,
    val teamList: List<TeamInfo>
)
