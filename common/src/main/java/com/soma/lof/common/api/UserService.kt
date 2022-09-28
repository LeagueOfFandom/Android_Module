package com.soma.lof.common.api

import com.soma.lof.core_model.dto.CommonItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface UserService {
    @POST("/user")
    suspend fun postUserToken(@Body userTokenRequest: com.soma.lof.core_model.dto.UserTokenRequest) : Response<com.soma.lof.core_model.dto.UserTokenResponse>

    @POST("/fcm")
    suspend fun postFcmToken(@Header("Authorization") jwtToken: String, @Body fcmToken: String) : Response<com.soma.lof.core_model.dto.UserTokenResponse>

    @GET("/mainPage")
    suspend fun getMainPage(@Header("Authorization") jwtToken: String) : Response<List<CommonItemResponse>>
}