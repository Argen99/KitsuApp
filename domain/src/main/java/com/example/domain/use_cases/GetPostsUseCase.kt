package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetPostsUseCase(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getPosts()
}