package com.soma.lof.common.data.dto

data class UserTokenResponse(
    val jwtToken : String,
    val newUser: Boolean
)