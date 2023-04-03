package com.example.domain.repository

import com.example.domain.either.Either
import com.example.domain.model.CategoriesData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCategories(): Flow<Either<String, List<CategoriesData>>>
}