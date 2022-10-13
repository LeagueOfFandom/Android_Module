package com.soma.lof.common.domain

import com.soma.lof.common.repository.TeamRepository
import com.soma.lof.core_model.dto.domain.SelectTeamModel
import com.soma.lof.foundation.exception.JwtTokenEmptyException
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

/**
 *  [SelectTeamModel] is Domain Layer Model
 */
class TeamUseCase @Inject constructor(
    private val teamRepository: TeamRepository,
) {
    suspend fun getSelectTeamData(jwtToken: String?): Flow<Result<SelectTeamModel>> {
        return flow {
            emit(Result.Loading)
            if (jwtToken == null) {
                emit(Result.Error(JwtTokenEmptyException()))
            } else {
                val data = SelectTeamModel()
                teamRepository.getSelectTeamList(jwtToken).collectLatest {
                    Timber.d("TeamUseCase getSelectTeamList Success")
                    data.leagueList = it.data?.leagueList ?: emptyList()
                    data.leagueInfo = it.data?.leagueInfo ?: emptyList()
                }
                teamRepository.getUserTeamList(jwtToken).collectLatest {
                    Timber.d("TeamUseCase getUserTeamList Success")
                    data.teamInfo = it.data?.toMutableList() ?: mutableListOf()
                }
                Timber.d("TeamUseCase emit data")
                emit(Result.Success(data))
            }
        }
    }
}