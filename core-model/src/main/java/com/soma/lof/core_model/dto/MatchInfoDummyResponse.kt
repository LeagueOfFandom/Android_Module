package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.MatchVO
import com.soma.lof.core_model.entity.PredictionData
import com.soma.lof.core_model.entity.RosterObject

data class MatchInfoDummyResponse(
    val setInfo: List<CommonItem>,
    val rosterInfo: RosterObject,
    val mainInfo: MatchVO,
    val prediction: PredictionData
)