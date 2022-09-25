package com.soma.lof.common.data.entity

data class MatchPreviewImageViewObject(
    val text: String,
    val blueImgList: List<String>,
    val redImgList: List<String>
) : ViewObject()