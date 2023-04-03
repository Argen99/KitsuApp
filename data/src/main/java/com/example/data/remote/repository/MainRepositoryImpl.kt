package com.example.data.remote.repository

import com.example.data.core.base.makeNetworkRequest
import com.example.data.remote.api_service.MainApiService
import com.example.data.remote.model.mappers.toModel
import com.example.domain.either.Either
import com.example.domain.model.CategoriesData
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

/**
 * Класс [MainRepositoryImpl] является реализацией интерфейса [MainRepository].
 * Он использует сервис API [MainApiService] для получения списка категорий.
 */
class MainRepositoryImpl(
    private val apiService: MainApiService
) : MainRepository {

    /**
     * Метод [getCategories] возвращает объект Flow<Either<String,List<CategoriesData>>>,
     * который содержит результат выполнения запроса на получение списка категорий.
     * Он использует функцию makeNetworkRequest для выполнения запроса и обработки возможных ошибок.
     */
    override fun getCategories(): Flow<Either<String, List<CategoriesData>>> = makeNetworkRequest {
        apiService.getCategories(300).data.map { it.toModel() }
    }
}