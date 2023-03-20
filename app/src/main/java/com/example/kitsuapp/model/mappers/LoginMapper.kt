package com.example.kitsuapp.model.mappers

import com.example.domain.model.LoginResponse
import com.example.kitsuapp.model.LoginResponseUI

fun LoginResponse.toUI() = LoginResponseUI(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)

fun LoginResponseUI.toModel() = LoginResponse(
    access_token = access_token,
    token_type = token_type,
    expires_in = expires_in,
    refresh_token = refresh_token,
    scope = scope,
    created_at = created_at
)