package com.soma.lof.core_model.dto

data class UserTokenRequest(
    val fcmToken: String?,
    val googleAccessToken: String
)
