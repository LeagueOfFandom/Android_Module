package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.LeagueTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Header

interface LeagueRepository {

    fun getSelectTeamList(jwtToken: String) : Flow<Result<LeagueTeamResponse>>

    suspend fun getUserTeamList(jwtToken: String) : Flow<Result<List<TeamInfo>>>

    suspend fun postUserTeamList(jwtToken: String, teamIdList: List<Long>) : Flow<Result<List<Long>>>

}