package com.soma.lof.common.repository.fake

import com.soma.lof.core_model.fake.FakeSelectTeamModel
import kotlinx.coroutines.flow.Flow

interface SelectTeamFakeRepository{

    fun getSelectTeamList() : Flow<FakeSelectTeamModel>

}