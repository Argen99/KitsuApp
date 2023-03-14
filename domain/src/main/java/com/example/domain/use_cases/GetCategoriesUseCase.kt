package com.example.domain.use_cases

import com.example.domain.repository.MainRepository

class GetCategoriesUseCase(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getCategories()
}