package com.soma.lof.core_model.dto

data class UserTokenResponse(
    val jwtToken : String,
    val newUser: Boolean
)