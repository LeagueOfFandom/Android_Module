package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.lof.core_model.dto.CreateUserResponse
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface UserRepository {

    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : Flow<Result<CreateUserResponse>>

    suspend fun setUserNickName(@Header("Authorization") jwtToken: String, @Body nickname: String) : Result<String>

    suspend fun getUserNickName(@Header("Authorization") jwtToken: String) : Result<String>

    @POST("/fcm")
    suspend fun postFcmToken(@Header("Authorization") jwtToken: String, @Body fcmToken: String) : Response<CreateUserResponse>

    suspend fun getUserInfo(@Header("Authorization") jwtToken: String) : List<CommonItemResponse>
}