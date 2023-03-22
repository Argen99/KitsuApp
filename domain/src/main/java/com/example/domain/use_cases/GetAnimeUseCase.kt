package com.example.domain.use_cases

import com.example.domain.repository.AnimeRepository

class GetAnimeUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(text: String?, categories: List<String>?) =
        repository.getAnime(text, categories)
}