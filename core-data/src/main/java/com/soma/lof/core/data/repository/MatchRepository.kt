package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.dto.MatchInfoDummyResponse
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun getMatchList(jwtString: String, date: String, onlyMyTeam: Boolean) : Flow<UiState<List<CommonItemResponse>>>

    fun getMatchInfoDataTest() : MatchInfoDummyResponse
}