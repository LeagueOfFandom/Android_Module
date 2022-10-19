package com.soma.lof.common.api

import com.soma.lof.core_model.dto.CreateUserRequest
import com.soma.lof.core_model.dto.CreateUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

interface UserService {

    @POST("/v1/users")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : CreateUserResponse

    @POST("/v1/users/nickname")
    suspend fun setUserNickName(@Header("Authorization") jwtToken: String, @Body nickname: String) : String

    @GET("/v1/users/nickname")
    suspend fun getUserNickName(@Header("Authorization") jwtToken: String) : String
}