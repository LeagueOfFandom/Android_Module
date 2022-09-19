package com.soma.lof.common.data.dto

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.DateInfo
import com.soma.lof.common.data.entity.MatchViewData

data class MatchResponse(
    val dateList: List<DateInfo>,
    val matchDataList: List<List<CommonItem>>,
)