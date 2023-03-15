package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetMangaUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(text: String?, categories: List<String>?) =
        repository.getManga(text, categories)
}