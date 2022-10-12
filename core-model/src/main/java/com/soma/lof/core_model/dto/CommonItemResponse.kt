package com.soma.lof.core_model.dto

import com.soma.lof.core_model.entity.CommonVO

/**
 * This data class is a form for obtaining a CommonList form
 */
data class CommonItemResponse(
    val viewType: String,
    val viewObject: CommonVO,
)