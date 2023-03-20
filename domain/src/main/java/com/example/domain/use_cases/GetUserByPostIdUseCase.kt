package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetUserByPostIdUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(id: String) = repository.getUserByPostId(id)
}