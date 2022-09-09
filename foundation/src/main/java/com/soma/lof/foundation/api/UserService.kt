package com.soma.lof.foundation.api

import com.soma.lof.foundation.data.dto.UserTokenRequest
import com.soma.lof.foundation.data.dto.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun postUserToken(@Body userTokenRequest: UserTokenRequest) : Response<UserTokenResponse>

    @POST("/fcm")
    suspend fun postFcmToken(@Header("Authorization") jwtToken: String, @Body fcmToken: String) : Response<UserTokenResponse>
}