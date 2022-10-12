package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.MatchInfoDummyResponse
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    suspend fun getMatchList(jwtString: String, date: String, isAll: Boolean) : Flow<Result<List<CommonItemResponse>>>

    suspend fun getMatchInfoDataTest() : MatchInfoDummyResponse
}