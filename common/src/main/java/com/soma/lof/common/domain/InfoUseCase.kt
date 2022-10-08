package com.soma.lof.common.domain

import com.soma.lof.common.repository.MatchRepository
import com.soma.lof.common.repository.UserRepository
import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getUserInfo(jwtToken: String) =
        userRepository.getUserInfo(jwtToken).toCommonItemList()
}