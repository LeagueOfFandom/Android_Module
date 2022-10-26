package com.soma.lof.core.data.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.MatchInfoDummyResponse
import com.soma.lof.core_network.result.UiState
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun getMatchList(jwtString: String, date: String, onlyMyTeam: Boolean) : Flow<UiState<List<CommonItemResponse>>>

    suspend fun getMatchInfoDataTest() : MatchInfoDummyResponse
}