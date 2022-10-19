package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.lof.core_model.dto.CreateUserResponse
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Header

interface UserRepository {

    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : Flow<Result<CreateUserResponse>>

    suspend fun setUserNickName(@Header("Authorization") jwtToken: String, @Body nickname: String) : Flow<Result<String>>

    suspend fun getUserNickName(@Header("Authorization") jwtToken: String) : Flow<Result<String>>

    // Info Fragment Dummy Data
    suspend fun getUserInfo(@Header("Authorization") jwtToken: String) : List<CommonItemResponse>
}