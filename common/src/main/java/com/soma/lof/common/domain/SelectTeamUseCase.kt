package com.soma.lof.common.domain

import com.soma.lof.common.repository.LeagueRepository
import com.soma.lof.core_model.dto.domain.SelectTeamModel
import com.soma.lof.foundation.exception.JwtTokenEmptyException
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

/**
 *  [SelectTeamModel] is Domain Layer Model
 */
class SelectTeamUseCase @Inject constructor(
    private val teamRepository: LeagueRepository,
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
                    data.leagueList = it.data?.leagueNameList ?: emptyList()
                    data.leagueInfo = it.data?.leagueInfoList ?: emptyList()
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