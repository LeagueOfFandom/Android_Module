package com.soma.common.repository

import com.soma.common.model.dto.UserTokenRequest
import com.soma.common.model.dto.UserTokenResponse
import com.soma.common.result.UiState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRepository {

    @POST("/user")
    suspend fun postUserToken(@Body userTokenRequest: UserTokenRequest) : Flow<UiState<UserTokenResponse>>
}