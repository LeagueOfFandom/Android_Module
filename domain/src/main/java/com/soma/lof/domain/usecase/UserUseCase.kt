package com.soma.lof.domain.usecase

import android.accounts.NetworkErrorException
import com.soma.lof.core.data.repository.UserRepository
import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import com.soma.lof.core.model.dto.GetUserNicknameResponse
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
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
        return flow {
            userRepository.getUserNickName(jwtToken).collect {
                emit(UiState.Success(it.data?.nickname ?: ""))
            }
        }.catch {
            throw NetworkErrorException("GetUserNickName Network Error ${it.message}")
        }
    }
}