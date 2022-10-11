package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.SelectTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Singleton

@Singleton
interface TeamRepository {

    @GET("/teamList")
    fun getSelectTeamList(jwtToken: String) : Flow<Result<SelectTeamResponse>>

    @GET("/teamList/user")
    suspend fun getUserTeamList(@Header("Authorization") jwtToken: String) : Flow<Result<List<TeamInfo>>>
}