package com.soma.lof.select_team.repository

import com.soma.lof.foundation.api.TeamService
import com.soma.lof.foundation.data.dto.SelectTeamData
import com.soma.lof.foundation.data.dto.UserTokenResponse
import com.soma.lof.foundation.exception.EmptyBodyException
import com.soma.lof.foundation.exception.NetworkFailureException
import com.soma.lof.foundation.result.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectTeamRepositoryImpl @Inject constructor(
    private val teamService: TeamService
) : SelectTeamRepository {

    override fun getSelectTeamList(): Flow<UiState<List<SelectTeamData>>> = flow {
        val response = teamService.getSelectTeamList()

        if (response.isSuccessful) {
            val data: List<SelectTeamData> = response.body() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            emit(UiState.Success(data))
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch {
        throw NetworkFailureException("Network Error")
    }


}