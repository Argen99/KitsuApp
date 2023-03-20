package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.ApiService
import com.example.data.remote.model.*
import com.example.data.remote.model.mappers.toModel
import com.example.data.remote.paging_src.AnimePagingSource
import com.example.data.remote.paging_src.MangaPagingSource
import com.example.data.remote.paging_src.PostsPagingSource
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

    override fun getUsersByName(name: String?): Flow<Resource<List<User>>> = doRequestList {
        apiService.getUsersByName(name).toModel().data
    }

    override fun getCategories(): Flow<Resource<List<CategoriesData>>> = doRequestList {
        apiService.getCategories(300).data.map { it.toModel() }
    }

    override fun login(username: String, password: String): Flow<Resource<LoginResponse>> = doRequest {
        apiService.login(LoginRequestDto(username = username, password = password)).toModel()
    }

    override fun getPosts(): Flow<PagingData<PostsData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                PostsPagingSource(apiService = apiService)
            }
        ).flow
    }

    override suspend fun getUserByPostId(id: String): User {
        return apiService.getUserByPostId(id).toModel().data
    }

    override fun createPost(
        userId: String,
        content: String,
        nsfw: Boolean,
        spoiler: Boolean
    ): Flow<Resource<Unit>> = doRequest {
        apiService.createPost(
            CreatePostDto(
                CreatePostDataDto(
                    CreatePostAttributesDto(
                        content = content,
                        nsfw = nsfw,
                        spoiler = spoiler
                    ),
                    CreatePostRelationshipsDto(
                        CreatePostUserDto(
                            CreatePostUserDataDto(
                                id = userId
                            )
                        ),
                        CreatePostUploadsDto()
                    )

                )
            )
        )
    }
}