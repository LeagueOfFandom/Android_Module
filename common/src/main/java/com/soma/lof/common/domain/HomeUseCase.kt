package com.soma.lof.common.domain

import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getHomeData(jwtToken: String, onlyMyTeam: Boolean = true): Flow<Result<List<CommonItem>>> {
        return flow {
            emit(Result.Loading)
            homeRepository.getMainPage(jwtToken, onlyMyTeam).collectLatest {
                emit(Result.Success(it.data?.toCommonItemList() ?: emptyList()))
            }
        }
    }

}