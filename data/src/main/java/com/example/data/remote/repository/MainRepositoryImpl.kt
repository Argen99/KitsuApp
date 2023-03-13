package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.remote.api_service.ApiService
import com.example.data.remote.paging_src.AnimePagingSource
import com.example.data.remote.paging_src.UsersPagingSource
import com.example.domain.model.Data
import com.example.domain.model.User
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: ApiService
): MainRepository {

    override fun getAnime(text: String?): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                AnimePagingSource(apiService = apiService, true, text = text)
            }
        ).flow
    }

    override fun getManga(text: String?): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                AnimePagingSource(apiService = apiService, false, text = text)
            }
        ).flow
    }

    override fun getUsers(text: String): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService = apiService, text = text)
            }
        ).flow
    }
}