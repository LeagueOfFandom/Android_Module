package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.CommonInfoItem
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.UserTokenRequest
import com.soma.lof.core_model.dto.UserTokenResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface UserRepository {

    @POST("/user")
    suspend fun postUserToken(@Body userTokenRequest: UserTokenRequest) : Flow<UserTokenResponse>

    @POST("/fcm")
    suspend fun postFcmToken(@Header("Authorization") jwtToken: String, @Body fcmToken: String) : Response<UserTokenResponse>

    suspend fun getUserInfo(@Header("Authorization") jwtToken: String) : List<CommonItemResponse>
}