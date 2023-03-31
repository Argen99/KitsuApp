package com.example.data.remote.api_service

import com.example.data.remote.model.AnimeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [AnimeApiService] Api Service для работы с аниме
 */
interface AnimeApiService {
    /**
     * [getManga] Получает список аниме с сервера.
     * @param limit Максимальное количество элементов, которое будет возвращено в списке.
     * @param offset Cмещение элементов.
     * @param text Текст для поиска.
     * @param categories Список категорий, по которым будет производиться фильтрация.
     * @return [AnimeResponseDto] с информацией о манге.
     */
    @GET("api/edge/anime")
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ): AnimeResponseDto
}