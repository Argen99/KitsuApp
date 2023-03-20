package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class LoginUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(username: String, password: String) = repository.login(username, password)
}