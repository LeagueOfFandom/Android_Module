package com.soma.lof.core_model.entity

data class MatchPreviewImageVO(
    val text: String,
    val blueImgList: List<String>,
    val redImgList: List<String>
) : ViewObject()