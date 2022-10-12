package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.CommonVO

data class CommonItemResponse(
    val viewType: String,
    val viewObject: CommonVO,
)