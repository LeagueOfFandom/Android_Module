package com.soma.lof.domain.repository.fake

import com.soma.lof.core.model.fake.FakeSelectTeamModel
import kotlinx.coroutines.flow.Flow

interface SelectTeamFakeRepository{

    fun getSelectTeamList() : Flow<FakeSelectTeamModel>

}