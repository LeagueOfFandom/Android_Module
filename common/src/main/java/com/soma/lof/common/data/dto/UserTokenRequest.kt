package com.soma.lof.common.data.dto

data class UserTokenRequest(
    val fcmToken: String?,
    val googleAccessToken: String
)
