package com.soma.lof.domain.usecase

import com.soma.lof.core.data.repository.LeagueRepository
import com.soma.lof.domain.model.SelectTeamModel
import com.soma.lof.core.network.exception.JwtTokenEmptyException
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *  [SelectTeamModel] is Domain Layer Model
 */
class SelectTeamUseCase @Inject constructor(
    private val leagueRepository: LeagueRepository,
) {
    suspend fun getSelectTeamData(jwtToken: String?): Flow<UiState<SelectTeamModel>> {
        return flow {
            emit(UiState.Loading)
            if (jwtToken == null) {
                emit(UiState.Error(JwtTokenEmptyException()))
            } else {
                val data = SelectTeamModel()
                leagueRepository.getSelectTeamList(jwtToken).collectLatest {
                    data.leagueList = it.data?.leagueNameList ?: emptyList()
                    data.leagueInfo = it.data?.leagueInfoList ?: emptyList()
                }
                leagueRepository.getUserTeamList(jwtToken).collectLatest {
                    data.teamInfo = it.data?.toMutableList() ?: mutableListOf()
                }
                emit(UiState.Success(data))
            }
        }
    }

    suspend fun postSelectTeamData(
        jwtToken: String?,
        teamList: List<Long>,
    ): Flow<UiState<Boolean>> {
        return flow {
            emit(UiState.Loading)
            if (jwtToken == null) {
                emit(UiState.Error(JwtTokenEmptyException()))
            } else {
                leagueRepository.postUserTeamList(jwtToken, teamList).collect {
                    emit(UiState.Success(true))
                }
            }
        }
    }
}