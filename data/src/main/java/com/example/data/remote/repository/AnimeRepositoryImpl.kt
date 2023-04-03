package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.remote.api_service.AnimeApiService
import com.example.data.remote.paging_src.AnimePagingSource
import com.example.domain.model.Data
import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Класс [AnimeRepositoryImpl] является реализацией интерфейса [AnimeRepository].
 * Он использует сервис API [AnimeApiService] для получения данных о аниме.
 */
class AnimeRepositoryImpl(
    private val apiService: AnimeApiService
) : AnimeRepository {
    /**
     * Метод [getAnime] возвращает объект Flow<PagingData<Data>> для постраничного
     * получения данных об аниме. Он использует объект Pager для настройки параметров
     * постраничного получения и pagingSourceFactory для создания объекта AnimePagingSource,
     * который реализует загрузку страниц данных. В метод можно передать параметры
     * для поиска и фильтрации по категориям.
     */
    override fun getAnime(text: String?, categories: List<String>?): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                AnimePagingSource(
                    apiService = apiService, text = text, categories = categories
                )
            }
        ).flow
    }
}