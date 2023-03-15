package com.example.kitsuapp.model.mappers

import com.example.domain.model.LoginRequest
import com.example.domain.model.LoginResponse
import com.example.kitsuapp.model.LoginRequestUI
import com.example.kitsuapp.model.LoginResponseUI

fun LoginRequest.toUI() = LoginRequestUI(
    grant_type = grant_type,
    username = username,
    password = password
)

fun LoginResponse.toUI() = LoginResponseUI(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)

fun LoginRequestUI.toModel() = LoginRequest(
    grant_type = grant_type,
    username = username,
    password = password
)

fun LoginResponseUI.toModel() = LoginResponse(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)