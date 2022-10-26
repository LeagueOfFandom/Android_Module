package com.soma.lof.domain.usecase

import com.soma.lof.core.data.repository.UserRepository
import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import com.soma.lof.core.result.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun createUser(createUserRequest: CreateUserRequest): Flow<UiState<CreateUserResponse>> {
        return userRepository.createUser(createUserRequest)
    }

    suspend fun setUserNickName(jwtToken: String, nickName: String): Flow<UiState<String>> {
        return userRepository.setUserNickName(jwtToken, nickName)
    }

    suspend fun getUserNickName(jwtToken: String): Flow<UiState<String>> {
        return userRepository.getUserNickName(jwtToken)
    }
}