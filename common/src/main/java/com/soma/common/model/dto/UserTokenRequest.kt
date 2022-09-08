package com.soma.common.model.dto

data class UserTokenRequest(
    val fcmToken: String,
    val googleAccessToken: String
)
