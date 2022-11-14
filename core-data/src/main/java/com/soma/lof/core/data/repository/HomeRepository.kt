package com.soma.lof.core.data.repository

import com.soma.lof.core.model.dto.MainPageResponse
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getMainPage(jwtToken: String, onlyMyTeam: Boolean) : Flow<UiState<MainPageResponse>>

    suspend fun getFakeMainPage(jwtToken: String, onlyMyTeam: Boolean) : Flow<UiState<MainPageResponse>>

}