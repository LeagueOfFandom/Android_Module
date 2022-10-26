package com.soma.lof.core.service

import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("/v1/users")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : CreateUserResponse

    @POST("/v1/users/nickname")
    suspend fun setUserNickName(@Header("Authorization") jwtToken: String, @Body nickname: String) : String

    @GET("/v1/users/nickname")
    suspend fun getUserNickName(@Header("Authorization") jwtToken: String) : String

    @GET("/v1/users/alarm")
    suspend fun getUserAlarmSetting(@Header("Authorization") jwtToken: String) : Boolean

    @POST("/v1/users/alarm")
    suspend fun updateUserAlarmSetting(@Header("Authorization") jwtToken: String, @Body alarm: Boolean) : Boolean
}