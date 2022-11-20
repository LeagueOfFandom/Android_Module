package com.soma.lof.domain.usecase

import android.accounts.NetworkErrorException
import com.soma.lof.core.data.repository.UserRepository
import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import com.soma.lof.core.model.dto.UserNicknameResponse
import com.soma.lof.core.model.entity.NewUserResponse
import com.soma.lof.core.result.UiState
import com.soma.lof.core.result.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun isNewUser(email: String): Flow<UiState<NewUserResponse>> {
        return userRepository.isNewUser(email)
    }

    suspend fun createUser(createUserRequest: CreateUserRequest): Flow<UiState<CreateUserResponse>> {
        return userRepository.createUser(createUserRequest)
    }

    suspend fun setUserNickName(jwtToken: String, nickName: String): Flow<UiState<String>> {
        return flow {
            userRepository.setUserNickName(jwtToken, nickName).collect {
                emit(UiState.Success(it.data?.nickname ?: ""))
            }
        }.catch {
            throw NetworkErrorException("GetUserNickName Network Error ${it.message}")
        }
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

    suspend fun updateFCM(jwtToken: String, fcmToken: String) {
        userRepository.updateFCM(jwtToken, fcmToken)
    }
}