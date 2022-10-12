package com.soma.lof.common.api

import com.soma.lof.core_model.dto.CommonItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MatchService {

    @GET("/matchList")
    suspend fun getMatchList(@Header("Authorization") jwtToken: String,
                             @Query("date") date: String,
                             @Query("all") isAll: Boolean) :List<CommonItemResponse>
}