package com.soma.lof.domain.usecase

import com.soma.lof.core.data.repository.CommunityRepository
import com.soma.lof.core.data.repository.HomeRepository
import com.soma.lof.core.model.entity.PostItem
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import com.soma.lof.domain.model.HomeModel
import com.soma.lof.domain.util.CommonItemTranslator.toCommonItemList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommunityUseCase @Inject constructor(
    private val communityRepository: CommunityRepository,
){

    suspend fun getFakeCommunityData(
        jwtToken: String
    ): Flow<UiState<List<PostItem>>> {
        return communityRepository.getFakeCommunitydata(jwtToken)
    }
}