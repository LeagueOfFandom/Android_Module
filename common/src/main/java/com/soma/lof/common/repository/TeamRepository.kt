package com.soma.lof.common.repository

import com.soma.lof.foundation.data.dto.UserTokenRequest
import com.soma.lof.foundation.data.dto.UserTokenResponse
import com.soma.lof.foundation.data.entity.TeamInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Singleton

@Singleton
interface TeamRepository {

    @GET("/teamList/user")
    suspend fun getUserTeamList(@Header("Authorization") jwtToken: String) : Flow<List<TeamInfo>>
}