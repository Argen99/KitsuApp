package com.example.domain.use_cases

import com.example.domain.repository.PostRepository

class GetPostsUseCase(
    private val repository: PostRepository
) {
    operator fun invoke() = repository.getPosts()
}