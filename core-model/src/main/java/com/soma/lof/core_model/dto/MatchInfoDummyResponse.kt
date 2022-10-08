package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.MatchViewObject
import com.soma.lof.core_model.entity.PredictionData
import com.soma.lof.core_model.entity.RosterObject

data class MatchInfoDummyResponse(
    val setInfo: List<CommonItem>,
    val rosterInfo: RosterObject,
    val mainInfo: MatchViewObject,
    val prediction: PredictionData
)