package com.soma.lof.match.repository

import com.soma.lof.match.model.dto.FakeMatchDataResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface FakeMatchRepository {

    fun getMatchData() : Flow<FakeMatchDataResponse>

}