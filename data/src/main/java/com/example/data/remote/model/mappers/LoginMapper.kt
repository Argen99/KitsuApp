package com.example.data.remote.model.mappers

import com.example.data.remote.model.LoginResponseDto
import com.example.domain.model.LoginResponse


fun LoginResponseDto.toModel() = LoginResponse(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)

fun LoginResponse.toDto() = LoginResponseDto(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)