package com.soma.lof.core.model.entity

data class MatchPreviewTextVO(
    val text: String,
    val blueString: String,
    val redString: String
) : ViewObject()