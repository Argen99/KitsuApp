package com.example.domain.use_cases

import com.example.domain.model.LoginRequest
import com.example.domain.repository.MainRepository

class LoginUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(loginRequest: LoginRequest) = repository.login(loginRequest)
}