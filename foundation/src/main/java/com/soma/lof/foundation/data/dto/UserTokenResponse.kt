package com.soma.lof.foundation.data.dto

data class UserTokenResponse(
    val jwtToken : String,
    val newUser: Boolean
)