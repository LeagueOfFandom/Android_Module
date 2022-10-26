package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.LeagueTeamResponse
import com.soma.lof.core.model.entity.TeamInfo
import com.soma.lof.core.network.exception.NetworkFailureException
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val leagueService: com.soma.lof.core.service.LeagueService,
) : LeagueRepository {

    override fun getSelectTeamList(jwtToken: String): Flow<UiState<LeagueTeamResponse>> {
        return flow {
            val selectTeamList = leagueService.getLeagueTeamList(jwtToken)
            emit(UiState.Success(selectTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getUserTeamList(jwtToken: String): Flow<UiState<List<TeamInfo>>> {
        return flow {
            val userTeamList = leagueService.getUserTeam(jwtToken)
            emit(UiState.Success(userTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun postUserTeamList(
        jwtToken: String,
        teamIdList: List<Long>
    ): Flow<UiState<List<Long>>> {
        return flow {
            val userTeamList = leagueService.postUserTeam(jwtToken, teamIdList)
            emit(UiState.Success(userTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}