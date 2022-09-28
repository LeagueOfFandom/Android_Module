package com.soma.lof.common.repository

import com.soma.lof.core_model.dto.MatchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MatchRepository {
    fun getMatchData() : Flow<MatchResponse>
}