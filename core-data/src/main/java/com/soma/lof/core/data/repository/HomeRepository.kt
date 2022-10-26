package com.soma.lof.core.data.repository

import com.soma.lof.core_model.dto.MainPageResponse
import com.soma.lof.core_network.result.UiState
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getMainPage(jwtToken: String, onlyMyTeam: Boolean) : Flow<UiState<MainPageResponse>>
}