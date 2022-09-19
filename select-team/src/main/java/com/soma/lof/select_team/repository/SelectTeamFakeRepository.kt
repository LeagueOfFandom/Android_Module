package com.soma.lof.select_team.repository

import com.soma.lof.select_team.model.FakeSelectTeamModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface SelectTeamFakeRepository{

    fun getSelectTeamList() : Flow<FakeSelectTeamModel>

}