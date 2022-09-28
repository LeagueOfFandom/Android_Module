package com.soma.lof.select_team.repository

import com.soma.lof.core_model.dto.TeamResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface SelectTeamRepository{

    @GET("/teamList")
    fun getSelectTeamList(jwtToken: String) : Flow<TeamResponse>

}