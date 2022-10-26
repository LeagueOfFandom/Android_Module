package com.soma.lof.core.model.dto

import com.soma.lof.core.model.entity.CommonVO

/**
 * This data class is a form for obtaining a CommonList form
 */
data class CommonItemResponse(
    val viewType: String,
    val viewObject: CommonVO,
)