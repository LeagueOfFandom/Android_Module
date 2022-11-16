package com.soma.lof.core.data.repository

import com.soma.lof.core.model.entity.PostItem
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    suspend fun getFakeCommunitydata(jwtToken: String) : Flow<UiState<List<PostItem>>>

}