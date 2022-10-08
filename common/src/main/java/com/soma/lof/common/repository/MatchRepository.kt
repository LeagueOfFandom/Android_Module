package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.MatchInfoDummyResponse
import com.soma.lof.core_model.dto.MatchInfoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MatchRepository {
    suspend fun getMatchList(jwtString: String, date: String, isAll: Boolean) : List<CommonItemResponse>

    suspend fun getMatchInfoDataTest() : MatchInfoDummyResponse
}