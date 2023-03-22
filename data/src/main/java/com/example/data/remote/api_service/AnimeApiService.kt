package com.example.data.remote.api_service

import com.example.data.remote.model.AnimeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApiService {
    @GET("api/edge/anime")
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ): AnimeResponseDto
}