package com.soma.lof.core.model.dto

data class CreateUserResponse(
    val jwtToken : String,
    val isNewUser: Boolean
)