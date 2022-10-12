package com.soma.lof.core_model.dto

data class CreateUserResponse(
    val jwtToken : String,
    val isNewUser: Boolean
)