package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.ApiService
import com.example.data.remote.model.mappers.toDto
import com.example.data.remote.model.mappers.toModel
import com.example.data.remote.paging_src.AnimePagingSource
import com.example.data.remote.paging_src.MangaPagingSource
import com.example.data.remote.paging_src.UsersPagingSource
import com.example.domain.common.Resource
import com.example.domain.model.*
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: ApiService
) : BaseRepository(), MainRepository {

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

    override fun getCategories(): Flow<Resource<List<CategoriesData>>> = doRequestList {
        apiService.getCategories(300).data.map { it.toModel() }
    }

    override fun login(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> = doRequest {
        apiService.login(loginRequest.toDto()).toModel()
    }
}