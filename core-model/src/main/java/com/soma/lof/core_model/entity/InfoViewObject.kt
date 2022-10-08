package com.soma.lof.core_model.entity

data class InfoViewObject(
    val title: String="",
    val content: String="",
    val isCheck: Boolean=false,
    val timeCompare: String="",
    val dateTime: String=""
) : ViewObject()
