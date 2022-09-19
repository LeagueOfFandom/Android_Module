package com.soma.lof.match.model.dto

import com.soma.lof.match.model.entity.DateInfo
import com.soma.lof.match.model.entity.MatchData

data class FakeMatchDataResponse(
    val month: String,
    val dateList: List<DateInfo>,
    val matchDataList: List<List<MatchData>>
)