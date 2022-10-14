package com.soma.lof.common.domain

import com.soma.lof.common.repository.HomeRepository
import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.domain.HomeModel
import com.soma.lof.foundation.result.Result
import com.soma.lof.foundation.result.data
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getHomeData(jwtToken: String, onlyMyTeam: Boolean = false): Flow<Result<HomeModel>> {
        return flow {
            emit(Result.Loading)
            homeRepository.getMainPage(jwtToken, onlyMyTeam).collect {
                emit(Result.Success(HomeModel(it.data?.bannerList ?: emptyList(), it.data?.commonItemListResponse?.toCommonItemList() ?: emptyList())))
            }
        }
    }

}