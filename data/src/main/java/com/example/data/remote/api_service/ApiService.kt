package com.example.data.remote.api_service

import com.example.data.remote.model.AnimeResponseDto
import com.example.data.remote.model.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("anime")
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?
    ) : AnimeResponseDto

    @GET("manga")
    suspend fun getManga(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?
    ) : AnimeResponseDto

    @GET("users")
    suspend fun getUsers(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ) : UserResponseDto
}