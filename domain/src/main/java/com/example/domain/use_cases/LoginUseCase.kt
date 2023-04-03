package com.example.domain.use_cases

import com.example.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(username: String, password: String) = repository.login(username, password)
}