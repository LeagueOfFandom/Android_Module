package com.soma.lof.foundation.data.dto

data class UserTokenRequest(
    val fcmToken: String?,
    val googleAccessToken: String
)
