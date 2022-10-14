package com.soma.lof.core_model.dto.domain

import com.soma.lof.core_model.dto.CommonItem

data class HomeModel(
    val bannerList: List<String>,
    val commonItemList: List<CommonItem>,
)