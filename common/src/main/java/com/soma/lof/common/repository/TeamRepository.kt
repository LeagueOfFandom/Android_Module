package com.soma.lof.common.repository

import com.soma.lof.core_model.entity.TeamInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Singleton

@Singleton
interface TeamRepository {

    @GET("/teamList/user")
    suspend fun getUserTeamList(@Header("Authorization") jwtToken: String) : Flow<List<TeamInfo>>
}