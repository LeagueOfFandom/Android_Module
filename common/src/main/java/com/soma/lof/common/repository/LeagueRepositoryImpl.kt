package com.soma.lof.common.repository

import com.soma.lof.common.api.LeagueService
import com.soma.lof.core_model.dto.LeagueTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.exception.NetworkFailureException
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val leagueService: LeagueService,
) : LeagueRepository {

    override fun getSelectTeamList(jwtToken: String): Flow<Result<LeagueTeamResponse>> {
        return flow {
            val selectTeamList = leagueService.getLeagueTeamList(jwtToken)
            emit(Result.Success(selectTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getUserTeamList(jwtToken: String): Flow<Result<List<TeamInfo>>> {
        return flow {
            val userTeamList = leagueService.getUserTeam(jwtToken)
            emit(Result.Success(userTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun postUserTeamList(
        jwtToken: String,
        teamIdList: List<Long>
    ): Flow<Result<List<Long>>> {
        return flow {
            val userTeamList = leagueService.postUserTeam(jwtToken, teamIdList)
            emit(Result.Success(userTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}