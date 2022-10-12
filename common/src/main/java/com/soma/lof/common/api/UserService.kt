package com.soma.lof.common.api

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.lof.core_model.dto.CreateUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface UserService {

    @POST("/v1/user/create")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : Result<CreateUserResponse>

    @POST("/v1/user/nickname")
    suspend fun setUserNickName(@Header("Authorization") jwtToken: String, @Body nickname: String) : Result<String>

    @GET("/v1/user/nickname")
    suspend fun getUserNickName(@Header("Authorization") jwtToken: String) : Result<String>

    @POST("/fcm")
    suspend fun postFcmToken(@Header("Authorization") jwtToken: String, @Body fcmToken: String) : Response<com.soma.lof.core_model.dto.CreateUserResponse>

    @GET("/mainPage")
    suspend fun getMainPage(@Header("Authorization") jwtToken: String) : Response<List<CommonItemResponse>>
}