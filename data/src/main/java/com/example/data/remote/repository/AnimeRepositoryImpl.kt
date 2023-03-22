package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.AnimeApiService
import com.example.data.remote.paging_src.AnimePagingSource
import com.example.domain.model.Data
import com.example.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeRepositoryImpl(
    private val apiService: AnimeApiService
): BaseRepository(), AnimeRepository {

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