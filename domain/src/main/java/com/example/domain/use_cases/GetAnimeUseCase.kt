package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetAnimeUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(text: String?) = repository.getAnime(text)
}