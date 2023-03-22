package com.example.data.remote.api_service

import com.example.data.remote.model.*
import retrofit2.http.*

interface MainApiService {

    @GET("api/edge/categories")
    suspend fun getCategories(
        @Query("page[limit]") limit: Int
    ): CategoriesResponseDto
}