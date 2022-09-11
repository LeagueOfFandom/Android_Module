package com.soma.lof.foundation.api

import com.soma.lof.foundation.data.dto.SelectTeamData
import com.soma.lof.foundation.data.dto.TeamResponse
import com.soma.lof.foundation.data.entity.TeamInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TeamService {

    @GET("/teamList")
    suspend fun getSelectTeamList(@Header("Authorization") jwtToken: String): Response<TeamResponse>

    @POST("/teamList")
    suspend fun postSelectTeamList(
        @Header("Authorization") jwtToken: String,
        @Body userTeamList: List<Int>,
    ): Response<String>

    @GET("/teamList/user")
    suspend fun getUserTeamList(@Header("Authorization") jwtToken: String): Response<List<TeamInfo>>
}