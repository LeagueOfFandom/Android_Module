package com.soma.lof.common.api

import com.soma.lof.common.data.dto.UserTokenRequest
import com.soma.lof.common.data.dto.UserTokenResponse
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.CommonItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface UserService {
    @POST("/user")
    suspend fun postUserToken(@Body userTokenRequest: UserTokenRequest) : Response<UserTokenResponse>

    @POST("/fcm")
    suspend fun postFcmToken(@Header("Authorization") jwtToken: String, @Body fcmToken: String) : Response<UserTokenResponse>

    @GET("/mainPage")
    suspend fun getMainPage(@Header("Authorization") jwtToken: String) : Response<List<CommonItem>>

    @GET("/mainPage")
    suspend fun getMainPageTest(@Header("Authorization") jwtToken: String) : Response<List<CommonItemResponse>>
}