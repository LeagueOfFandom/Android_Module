package com.soma.lof.core.service

import com.soma.lof.core.model.dto.CreateUserRequest
import com.soma.lof.core.model.dto.CreateUserResponse
import com.soma.lof.core.model.dto.UserNicknameResponse
import com.soma.lof.core.model.entity.NewUserResponse
import retrofit2.http.*

interface UserService {

    @GET("/v1/users/new")
    suspend fun isNewUser(@Query("email") email: String) : NewUserResponse

    @POST("/v1/users")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : CreateUserResponse

    @POST("/v1/users/nickname")
    suspend fun setUserNickName(@Header("Authorization") jwtToken: String, @Body nickname: String) : UserNicknameResponse

    @GET("/v1/users/nickname")
    suspend fun getUserNickName(@Header("Authorization") jwtToken: String) : UserNicknameResponse

    @GET("/v1/users/alarm")
    suspend fun getUserAlarmSetting(@Header("Authorization") jwtToken: String) : Boolean

    @POST("/v1/users/alarm")
    suspend fun updateUserAlarmSetting(@Header("Authorization") jwtToken: String, @Body alarm: Boolean) : Boolean

    @POST("/v1/users/fcm")
    suspend fun updateFCM(@Header("Authorization") jwtToken: String, @Body fcmToken: String)
}