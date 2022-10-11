package com.soma.lof.common.repository

import com.soma.lof.common.api.TeamService
import com.soma.lof.core_model.dto.SelectTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.JwtTokenEmptyException
import com.soma.lof.foundation.exception.NetworkFailureException
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val teamService: TeamService,
) : TeamRepository {

    override fun getSelectTeamList(jwtToken: String): Flow<Result<SelectTeamResponse>> {
        return flow {
            val selectTeamList = teamService.getSelectTeamList(jwtToken)
            emit(Result.Success(selectTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }

    override suspend fun getUserTeamList(jwtToken: String): Flow<Result<List<TeamInfo>>> {
        return flow {
            val userTeamList = teamService.getUserTeam(jwtToken)
            emit(Result.Success(userTeamList))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}