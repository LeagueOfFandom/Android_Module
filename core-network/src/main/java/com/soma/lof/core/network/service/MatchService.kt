package com.soma.lof.core.service

import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.dto.MainPageResponse
import com.soma.lof.core.model.dto.MatchDetailResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MatchService {

    @GET("/v1/match/matchListByMonth")
    suspend fun getMatchList(@Header("Authorization") jwtToken: String,
                             @Query("date") date: String,
                             @Query("onlyMyTeam") onlyMyTeam: Boolean) :List<CommonItemResponse>


    @GET("/v1/match/mainPage")
    suspend fun getMainPage(@Header("Authorization") jwtToken: String, @Query("onlyMyTeam") onlyMyTeam: Boolean) : MainPageResponse

    @GET("/v1/match/matchDetail")
    suspend fun getMatchDetail(@Header("Authorization") jwtToken: String, @Query("matchId") matchId: Long) : MatchDetailResponse
}