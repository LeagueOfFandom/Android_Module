package com.soma.lof.common.usecase

import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core.data.repository.HomeRepository
import com.soma.lof.core_model.dto.domain.HomeModel
import com.soma.lof.core_network.result.UiState
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.soma.lof.core_network.result.data

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

}