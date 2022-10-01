package com.soma.lof.common.domain

import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core_model.dto.CommonItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
) {
    suspend fun getMatchList(jwtToken: String, date: String, isAll: Boolean): List<CommonItem> =
        matchRepository.getMatchList(jwtToken, date, isAll).toCommonItemList()
}