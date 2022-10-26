package com.soma.lof.core.model.dto

import com.soma.lof.core.model.entity.TeamInfo

data class SelectTeamData(
    val league: String,
    val teamList: List<TeamInfo>
)