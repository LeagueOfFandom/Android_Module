package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.LeagueTeamResponse
import com.soma.lof.core.model.entity.TeamInfo
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow

interface LeagueRepository {

    fun getSelectTeamList(jwtToken: String) : Flow<UiState<LeagueTeamResponse>>

    suspend fun getUserTeamList(jwtToken: String) : Flow<UiState<List<TeamInfo>>>

    suspend fun postUserTeamList(jwtToken: String, teamIdList: List<Long>) : Flow<UiState<List<Long>>>

}