package com.soma.lof.common.repository

import com.soma.lof.common.data.dto.MatchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MatchRepository {
    fun getMatchData() : Flow<MatchResponse>
}