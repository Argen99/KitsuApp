package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.remote.api_service.MangaApiService
import com.example.data.remote.paging_src.MangaPagingSource
import com.example.domain.model.Data
import com.example.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

/**
 * Класс [MangaRepositoryImpl] является реализацией интерфейса [MangaRepository].
 * Он использует сервис API [MangaApiService] для получения данных о аниме.
 */
class MangaRepositoryImpl(
    private val apiService: MangaApiService
) : MangaRepository {
    /**
     * Метод [getManga] возвращает объект Flow<PagingData<Data>> для постраничного
     * получения данных об аниме. Он использует объект Pager для настройки параметров
     * постраничного получения и pagingSourceFactory для создания объекта [MangaPagingSource],
     * который реализует загрузку страниц данных. В метод можно передать параметры
     * для поиска и фильтрации по категориям.
     */
    override fun getManga(text: String?, categories: List<String>?): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                MangaPagingSource(
                    apiService = apiService, text = text, categories = categories
                )
            }
        ).flow
    }
}