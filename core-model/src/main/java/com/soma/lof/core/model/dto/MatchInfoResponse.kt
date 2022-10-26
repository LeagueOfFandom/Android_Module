package com.soma.lof.core.model.dto

import com.soma.lof.core.model.entity.MatchVO
import com.soma.lof.core.model.entity.PredictionData
import com.soma.lof.core.model.entity.RosterObject

data class MatchInfoResponse(
    val setInfo: List<CommonItemResponse>,
    val rosterInfo: RosterObject,
    val mainInfo: MatchVO,
    val prediction: PredictionData
)