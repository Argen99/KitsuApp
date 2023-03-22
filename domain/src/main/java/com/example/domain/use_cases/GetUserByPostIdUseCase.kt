package com.example.domain.use_cases

import com.example.domain.repository.UserRepository

class GetUserByPostIdUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String) = repository.getUserByPostId(id)
}