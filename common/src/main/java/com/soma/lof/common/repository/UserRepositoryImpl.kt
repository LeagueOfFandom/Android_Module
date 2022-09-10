package com.soma.lof.common.repository

import com.soma.lof.foundation.data.dto.UserTokenRequest
import com.soma.lof.foundation.data.dto.UserTokenResponse
import com.soma.lof.foundation.exception.NetworkFailureException
import com.soma.lof.foundation.api.UserService
import com.soma.lof.foundation.exception.EmptyBodyException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun postUserToken(userTokenRequest: UserTokenRequest): Flow<UserTokenResponse> =
        flow {
            val response = userService.postUserToken(userTokenRequest)
            if (response.isSuccessful) {
                val data: UserTokenResponse = response.body() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
                emit(data)
            } else {
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { throw NetworkFailureException("Network Error") }

    override suspend fun postFcmToken(
        jwtToken: String,
        fcmToken: String,
    ): Response<UserTokenResponse> {
        TODO("Not yet implemented")
    }
}