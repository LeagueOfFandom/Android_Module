package com.soma.lof.common.data.entity

data class CommunityViewObject(
    val nickname: String,
    val profileImg: String,
    val time: String,
    val content: String
) : ViewObject()