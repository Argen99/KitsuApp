package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetUserByNameUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(name: String) = repository.getUsersByName(name)
}