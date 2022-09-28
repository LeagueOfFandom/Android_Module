package com.soma.lof.common.repository

import com.soma.lof.common.api.TeamService
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.NetworkFailureException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(
    private val teamService: TeamService,
) : TeamRepository {

    override suspend fun getUserTeamList(jwtToken: String): Flow<List<TeamInfo>> =
        flow {
            val response = teamService.getUserTeamList(jwtToken)

            if (response.isSuccessful) {
                emit(response.body()
                    ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}"))
            } else {
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { throw NetworkFailureException("Network Error") }
}