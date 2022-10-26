package com.soma.lof.common.usecase

import com.soma.lof.common.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core.data.repository.UserRepository
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getUserInfo(jwtToken: String) =
        userRepository.getUserInfo(jwtToken).toCommonItemList()
}