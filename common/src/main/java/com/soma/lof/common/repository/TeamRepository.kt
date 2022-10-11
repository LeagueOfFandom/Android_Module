package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.SelectTeamResponse
import com.soma.lof.core_model.entity.TeamInfo
import com.soma.lof.foundation.result.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Header

interface TeamRepository {

    fun getSelectTeamList(jwtToken: String) : Flow<Result<SelectTeamResponse>>

    suspend fun getUserTeamList(@Header("Authorization") jwtToken: String) : Flow<Result<List<TeamInfo>>>
}