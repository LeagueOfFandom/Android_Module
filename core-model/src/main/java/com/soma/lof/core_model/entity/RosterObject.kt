package com.soma.lof.core_model.entity

data class RosterObject(
    val blueTeam: List<RosterData>,
    val redTeam: List<RosterData>,
)

data class RosterData(
    val position: String,
    val name: String,
    val positionImg: String
)
