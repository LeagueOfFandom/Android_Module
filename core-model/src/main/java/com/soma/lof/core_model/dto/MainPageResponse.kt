package com.soma.lof.core_model.dto

data class MainPageResponse(
    val bannerList: List<String>,
    val commonItemList: List<CommonItemResponse>,
)
