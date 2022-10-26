package com.soma.lof.core.model.entity

data class InfoVO(
    val title: String="",
    val content: String="",
    val isCheck: Boolean=false,
    val timeCompare: String="",
    val dateTime: String=""
) : ViewObject()
