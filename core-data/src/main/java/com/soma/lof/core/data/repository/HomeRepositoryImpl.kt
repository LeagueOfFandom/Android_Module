package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.dto.MainPageResponse
import com.soma.lof.core.network.exception.NetworkFailureException
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [CommonItemResponse] is for obtaining CommonList Data Form
 * CommonList is to apply ViewHolder and Data according to ViewType for Easy Recycling.
 */
class HomeRepositoryImpl @Inject constructor(
    private val matchService: com.soma.lof.core.service.MatchService
) : HomeRepository {

    override suspend fun getMainPage(
        jwtToken: String,
        onlyMyTeam: Boolean,
    ): Flow<UiState<MainPageResponse>> {
        return flow {
            val mainPageData = matchService.getMainPage(jwtToken, onlyMyTeam)
            emit(UiState.Success(mainPageData))
        }.catch {
            throw NetworkFailureException("Network Error ${it.message}")
        }
    }
}