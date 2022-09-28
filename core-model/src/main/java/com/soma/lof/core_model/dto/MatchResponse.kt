package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.DateInfo

data class MatchResponse(
    val dateList: List<DateInfo>,
    val matchDataList: List<List<CommonItem>>,
)