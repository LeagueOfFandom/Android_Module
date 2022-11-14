package com.soma.lof.domain.usecase

import com.soma.lof.domain.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core.data.repository.HomeRepository
import com.soma.lof.domain.model.HomeModel
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.soma.lof.core.result.data

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend fun getHomeData(
        jwtToken: String,
        onlyMyTeam: Boolean = false,
    ): Flow<UiState<HomeModel>> {
        return flow {
            emit(UiState.Loading)
            homeRepository.getMainPage(jwtToken, onlyMyTeam).collect {
                emit(UiState.Success(HomeModel(it.data?.bannerList ?: emptyList(),
                    it.data?.commonItemList?.toCommonItemList() ?: emptyList())))
            }
        }
    }

    suspend fun getFakeHomeData(
        jwtToken: String,
        onlyMyTeam: Boolean = false
    ): Flow<UiState<HomeModel>> {
        return flow {
            emit(UiState.Loading)
            homeRepository.getFakeMainPage(jwtToken, onlyMyTeam).collect {
                emit(UiState.Success(HomeModel(it.data?.bannerList ?: emptyList(),
                    it.data?.commonItemList?.toCommonItemList() ?: emptyList())))
            }
        }
    }

}