package com.soma.lof.core_model.dto

/**
 * This data is for creating user
 */
data class CreateUserRequest(
    val email: String,
    val name: String,
    val fcmToken: String?,
    val picture: String
)
