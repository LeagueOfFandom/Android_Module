package com.soma.lof.domain.model

import com.soma.lof.core.model.dto.CommonItem

data class HomeModel(
    val bannerList: List<String>,
    val commonItemList: List<CommonItem>,
)