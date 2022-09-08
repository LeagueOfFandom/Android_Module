package com.soma.common.model.dto

data class UserTokenResponse(
    val jwtToken : String,
    val newUser: Boolean
)