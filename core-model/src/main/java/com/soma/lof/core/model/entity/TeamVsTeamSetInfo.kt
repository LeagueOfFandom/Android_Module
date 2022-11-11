package com.soma.lof.core.model.entity

data class TeamVsTeamSetInfo(
    val teamVsTeamSetInfo: List<MatchPreviewImageVO>,
    val teamVsTeamRosterInfo: RosterObject,
    val teamVsTeamMainInfo: TeamVsTeamMainInfo,
    val teamVsTeamPrediction: PredictionData
)
