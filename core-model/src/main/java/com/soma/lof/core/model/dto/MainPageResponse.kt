package com.soma.lof.core.model.dto

data class MainPageResponse(
    val bannerList: List<String>,
    val commonItemList: List<CommonItemResponse>,
)
