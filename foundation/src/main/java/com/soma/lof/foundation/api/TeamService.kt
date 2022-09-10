package com.soma.lof.foundation.api

import com.soma.lof.foundation.data.dto.SelectTeamData
import retrofit2.Response
import retrofit2.http.GET

interface TeamService {

    @GET("/teamList")
    suspend fun getSelectTeamList() : Response<List<SelectTeamData>>
}