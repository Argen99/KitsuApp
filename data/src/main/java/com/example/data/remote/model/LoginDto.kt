package com.example.data.remote.model

data class LoginRequestDto(
    val grant_type: String,
    val username: String,
    val password: String
)

data class LoginResponseDto(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
    val created_at: Int
)