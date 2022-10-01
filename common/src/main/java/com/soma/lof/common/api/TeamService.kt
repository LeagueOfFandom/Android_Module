package com.soma.lof.common.api

import com.soma.lof.core_model.entity.TeamInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface TeamService {

    @GET("/teamList")
    suspend fun getSelectTeamList(@Header("Authorization") jwtToken: String): Response<com.soma.lof.core_model.dto.SelectTeamResponse>

    @POST("/teamList")
    suspend fun postSelectTeamList(
        @Header("Authorization") jwtToken: String,
        @Body userTeamList: List<Int>,
    ): Response<String>

    @GET("/teamList/user")
    suspend fun getUserTeamList(@Header("Authorization") jwtToken: String): Response<List<TeamInfo>>
}