package com.soma.lof.select_team.repository

import com.soma.lof.foundation.data.dto.SelectTeamData
import com.soma.lof.foundation.data.dto.TeamResponse
import com.soma.lof.foundation.result.UiState
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface SelectTeamRepository{

    @GET("/teamList")
    fun getSelectTeamList(jwtToken: String) : Flow<TeamResponse>

}