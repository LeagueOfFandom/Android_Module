package com.soma.lof.domain.usecase

import com.soma.lof.domain.util.CommonItemTranslator
import com.soma.lof.domain.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core.data.repository.MatchRepository
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * We need to translate [CommonItemResponse] to [CommonItem]
 * [toCommonItemList] is a func of [CommonItemTranslator]
 */
class MatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
) {

    suspend fun getMatchList(
        jwtToken: String,
        date: String,
        onlyMyTeam: Boolean,
    ): Flow<UiState<List<CommonItem>>> {
        return flow {
            matchRepository.getMatchList(jwtToken, date, onlyMyTeam).collect {
                emit(UiState.Success(it.data?.toCommonItemList() ?: emptyList()))
            }
        }
    }
}