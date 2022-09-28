package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.TeamInfo

data class SelectTeamData(
    val league: String,
    val teamList: List<TeamInfo>
)