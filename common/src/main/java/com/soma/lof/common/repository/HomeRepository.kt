package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.MainPageResponse
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface HomeRepository {
    suspend fun getMainPage(jwtToken: String, onlyMyTeam: Boolean) : Flow<Result<MainPageResponse>>

}