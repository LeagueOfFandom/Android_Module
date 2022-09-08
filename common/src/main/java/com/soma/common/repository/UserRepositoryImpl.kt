package com.soma.common.repository

import com.soma.common.exception.NetworkFailureException
import com.soma.common.model.dto.UserTokenRequest
import com.soma.common.model.dto.UserTokenResponse
import com.soma.common.network.UserService
import com.soma.common.result.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun postUserToken(userTokenRequest: UserTokenRequest): Flow<UiState<UserTokenResponse>> =
        flow<UiState<UserTokenResponse>> {
            val response = userService.postUserToken(userTokenRequest)
            if (response.isSuccessful) {
                val data: UserTokenResponse = response.body()!!
                emit(UiState.Success(data))
            } else {
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { UiState.Error(it) }
}