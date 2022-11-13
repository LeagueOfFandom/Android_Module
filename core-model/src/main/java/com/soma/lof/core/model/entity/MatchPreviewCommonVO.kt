package com.soma.lof.core.model.entity

data class MatchPreviewCommonVO(
    val viewType: String,
    val viewStringObject: MatchPreviewTextVO?,
    val viewImgObject: MatchPreviewImageVO?,
)