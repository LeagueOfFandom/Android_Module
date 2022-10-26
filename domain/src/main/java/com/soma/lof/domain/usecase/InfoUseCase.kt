package com.soma.lof.domain.usecase

import com.soma.lof.domain.util.CommonItemTranslator.toCommonItemList
import com.soma.lof.core.data.repository.UserRepository
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun getUserInfo(jwtToken: String) =
        userRepository.getUserInfo(jwtToken).toCommonItemList()
}