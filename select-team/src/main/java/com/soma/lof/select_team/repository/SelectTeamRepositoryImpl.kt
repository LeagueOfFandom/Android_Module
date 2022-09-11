package com.soma.lof.select_team.repository

import com.soma.lof.foundation.api.TeamService
import com.soma.lof.foundation.data.dto.TeamResponse
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.NetworkFailureException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectTeamRepositoryImpl @Inject constructor(
    private val teamService: TeamService
) : SelectTeamRepository {

    override fun getSelectTeamList(jwtToken: String): Flow<TeamResponse> = flow {
        val response = teamService.getSelectTeamList(jwtToken)

        if (response.isSuccessful) {
            val data = response.body() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            emit(data)
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch {
        throw NetworkFailureException("Network Error")
    }
}