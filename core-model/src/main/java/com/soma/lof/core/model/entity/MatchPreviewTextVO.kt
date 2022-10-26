package com.soma.lof.core.model.entity

data class MatchPreviewTextVO(
    val text: String,
    val blueData: String,
    val redData: String
) : ViewObject()