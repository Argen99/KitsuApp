package com.example.domain.use_cases

import com.example.domain.repository.UserRepository

class GetUserByNameUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(name: String) = repository.getUsersByName(name)
}