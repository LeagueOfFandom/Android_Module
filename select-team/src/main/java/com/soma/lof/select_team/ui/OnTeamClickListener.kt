package com.soma.lof.select_team.ui

import com.soma.lof.core_model.entity.TeamInfo

interface OnTeamClickListener {
    fun onClicked(team: TeamInfo, leaguePos: Int, teamPos: Int)
}