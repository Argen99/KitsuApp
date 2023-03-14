package com.example.data.remote.api_service

import com.example.data.remote.model.AnimeResponseDto
import com.example.data.remote.model.CategoriesDataDto
import com.example.data.remote.model.CategoriesResponseDto
import com.example.data.remote.model.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/edge/anime")
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ) : AnimeResponseDto

    @GET("api/edge/manga")
    suspend fun getManga(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ) : AnimeResponseDto

    @GET("api/edge/users")
    suspend fun getUsers(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ) : UserResponseDto

    @GET("api/edge/categories")
    suspend fun getCategories(
        @Query("page[limit]") limit: Int
    ) : CategoriesResponseDto
}