package com.soma.lof.common.api

import com.soma.lof.core_model.dto.SelectTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TeamService {

    @GET("/teamList")
    suspend fun getSelectTeamList(@Header("Authorization") jwtToken: String): SelectTeamResponse

    @GET("/teamList/user")
    suspend fun getUserTeam(@Header("Authorization") jwtToken: String) : List<TeamInfo>

    @POST("/teamList")
    suspend fun postSelectTeamList(
        @Header("Authorization") jwtToken: String,
        @Body userTeamList: List<Int>,
    ): Response<String>
}