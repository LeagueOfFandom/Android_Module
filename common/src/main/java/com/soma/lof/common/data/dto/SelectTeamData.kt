package com.soma.lof.common.data.dto

import com.soma.lof.common.data.entity.TeamInfo

data class SelectTeamData(
    val league: String,
    val teamList: List<TeamInfo>
)