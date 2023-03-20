package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class CreatePostUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(userId: String, content: String, nsfw: Boolean, spoiler: Boolean) =
        repository.createPost(userId = userId,content = content,nsfw = nsfw, spoiler = spoiler)
}