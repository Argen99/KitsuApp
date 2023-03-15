package com.example.data.remote.model.mappers

import com.example.data.remote.model.LoginRequestDto
import com.example.data.remote.model.LoginResponseDto
import com.example.domain.model.LoginRequest
import com.example.domain.model.LoginResponse

fun LoginRequestDto.toModel() = LoginRequest(
    grant_type = grant_type,
    username = username,
    password = password
)

fun LoginResponseDto.toModel() = LoginResponse(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)

fun LoginRequest.toDto() = LoginRequestDto(
    grant_type = grant_type,
    username = username,
    password = password
)

fun LoginResponse.toDto() = LoginResponseDto(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)