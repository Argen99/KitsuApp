package com.example.domain.use_cases

import com.example.domain.repository.MainRepository
import com.example.domain.repository.MangaRepository

class GetMangaUseCase(
    private val repository: MangaRepository
) {
    operator fun invoke(text: String?, categories: List<String>?) =
        repository.getManga(text, categories)
}