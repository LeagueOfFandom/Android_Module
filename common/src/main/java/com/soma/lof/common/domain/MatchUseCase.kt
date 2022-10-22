package com.soma.lof.common.domain

import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.util.CommonItemTranslator
import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

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
    ): Flow<Result<List<CommonItem>>> {
        return flow {
            matchRepository.getMatchList(jwtToken, date, onlyMyTeam).collect {
                emit(Result.Success(it.data?.toCommonItemList() ?: emptyList()))
            }
        }
    }
}