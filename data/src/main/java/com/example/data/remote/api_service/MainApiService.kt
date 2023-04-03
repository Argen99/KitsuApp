package com.example.data.remote.api_service

import com.example.data.remote.model.*
import retrofit2.http.*

/**
 * [MainApiService] Api Service для работы с остальными запросами
 */
interface MainApiService {
    /**
     * Получает список категорий с сервера.
     * @param limit Максимальное количество категорий на одной странице
     * @return Список категорий, упакованный в [CategoriesResponseDto]
     */
    @GET("api/edge/categories")
    suspend fun getCategories(
        @Query("page[limit]") limit: Int
    ): CategoriesResponseDto
}