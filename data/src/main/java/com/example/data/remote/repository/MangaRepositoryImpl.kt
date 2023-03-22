package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.MangaApiService
import com.example.data.remote.paging_src.MangaPagingSource
import com.example.domain.model.Data
import com.example.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class MangaRepositoryImpl(
    private val apiService: MangaApiService
): BaseRepository(), MangaRepository {
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