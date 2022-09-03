package com.soma.core_network.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitLofNetworkApi {

    /*@POST("/user")
    suspend fun postUser(@Body body: PostUserTokenBody) : UserTokenResponse

    @GET("/teamList")
    suspend fun getUserTeamList(@Query("id") userId: Long) : UserTeamListResponse

    @POST("/teamList")
    suspend fun updateUserTeamList(@Body body: PostUserTeamListBody) : String


    @GET("/matchList")
    suspend fun getUserMatches(
        @Query("id") userId: Long,
        @Query("isAfter") isAfterMatch: Boolean,
        @Query("all") isAllMatch: Boolean = false,
        @Query("page") page: Int = 0
    ): MatchesResponse

    @POST("/alarm")
    suspend fun setMatchAlarm(@Body body: UserAlarmBody)

    @GET("/teamVSteam")
    suspend fun getMatchInfoData(@Query("matchId") matchId: Long): MatchInfoResponse*/

    @GET("/teamRankList")
    suspend fun getTeamRankList(
        @Query("league") league: String,
        @Query("season") season: String,
        @Query("year") year: String
    ): TeamRankResponse
}