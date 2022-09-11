package com.soma.lof.select_team.repository

import com.soma.lof.foundation.data.dto.SelectTeamData
import com.soma.lof.foundation.result.UiState
import com.soma.lof.select_team.model.FakeSelectTeamModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface SelectTeamFakeRepository{

    fun getSelectTeamList() : Flow<FakeSelectTeamModel>

}