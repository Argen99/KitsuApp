package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.UserApiService
import com.example.data.remote.model.mappers.toModel
import com.example.data.remote.paging_src.UsersPagingSource
import com.example.domain.either.Either
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val apiService: UserApiService
) : BaseRepository(), UserRepository {

    override fun getUsers(name: String?): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService = apiService, name = name)
            }
        ).flow
    }

    override fun getUsersByName(name: String?): Flow<Either<String, List<User>>> = makeNetworkRequest {
        apiService.getUsersByName(name).toModel().data
    }

    override suspend fun getUserByPostId(id: String): User {
        return apiService.getUserByPostId(id).toModel().data
    }
}