package com.soma.core_model

data class TeamRank(
    val year: String,
    val season: String,
    val league: String,
    val rank: Int,
    val name: String,
    val teamImg: String,
    val seriesWinLose: String,
    val seriesWinRate: String,
    val gamesWinLose: String,
    val points: Int,
    val recentMatches: List<String>
)
