package com.soma.lof.foundation.data.dto

import com.soma.lof.foundation.data.entity.TeamInfo

data class SelectTeamData(
    val league: String,
    val teamList: List<TeamInfo>
)