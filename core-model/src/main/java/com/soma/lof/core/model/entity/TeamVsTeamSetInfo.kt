package com.soma.lof.core.model.entity

data class TeamVsTeamSetInfo(
    val teamVsTeamSetInfo: List<MatchPreviewCommonVO>,
    val teamVsTeamRosterInfo: RosterObject,
    val teamVsTeamMainInfo: TeamVsTeamMainInfo,
    val teamVsTeamPrediction: PredictionData
)
