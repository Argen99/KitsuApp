package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetUsersUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(text: String) = repository.getUsers(text)
}