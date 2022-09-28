package com.soma.lof.home.usecase

import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.home.repository.HomeRepository
import com.soma.lof.home.util.CommonItemTranslator.toCommonItemList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend fun getHomeData(jwtToken: String): List<CommonItem> =
        homeRepository.getMainPage(jwtToken).toCommonItemList()
}