package com.example.data.remote.api_service

import com.example.data.remote.model.AnimeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApiService {
    @GET("api/edge/manga")
    suspend fun getManga(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ): AnimeResponseDto


}