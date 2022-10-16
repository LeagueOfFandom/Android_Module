package com.soma.lof.common.api

import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.dto.MainPageResponse
import retrofit2.Response
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
}