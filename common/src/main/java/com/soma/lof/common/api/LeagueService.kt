package com.soma.lof.common.api

import com.soma.lof.core_model.dto.LeagueTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LeagueService {

    @GET("/v1/league/allByUser")
    suspend fun getLeagueTeamList(@Header("Authorization") jwtToken: String): LeagueTeamResponse

    @GET("/v1/league/selectedTeamByUser")
    suspend fun getUserTeam(@Header("Authorization") jwtToken: String) : List<TeamInfo>

    @POST("/v1/league/selectedTeamByUser")
    suspend fun postUserTeam(@Header("Authorization") jwtToken: String, @Body teamIdList: List<Long>) : List<Long>

    @POST("/teamList")
    suspend fun postSelectTeamList(
        @Header("Authorization") jwtToken: String,
        @Body userTeamList: List<Int>,
    ): Response<String>
}