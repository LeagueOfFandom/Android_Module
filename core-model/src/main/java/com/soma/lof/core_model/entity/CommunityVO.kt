package com.soma.lof.core_model.entity

data class CommunityVO(
    val nickname: String,
    val profileImg: String,
    val time: String,
    val content: String
) : ViewObject()