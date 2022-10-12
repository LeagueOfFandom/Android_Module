package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.MatchVO
import com.soma.lof.core_model.entity.PredictionData
import com.soma.lof.core_model.entity.RosterObject

data class MatchInfoResponse(
    val setInfo: List<CommonItemResponse>,
    val rosterInfo: RosterObject,
    val mainInfo: MatchVO,
    val prediction: PredictionData
)