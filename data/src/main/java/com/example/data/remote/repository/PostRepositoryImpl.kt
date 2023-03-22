package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.PostApiService
import com.example.data.remote.model.*
import com.example.data.remote.paging_src.PostsPagingSource
import com.example.domain.either.Either
import com.example.domain.model.PostsData
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val apiService: PostApiService
) : BaseRepository(), PostRepository {

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

    override fun createPost(
        userId: String,
        content: String,
        nsfw: Boolean,
        spoiler: Boolean
    ): Flow<Either<String ,Unit>> = makeNetworkRequest {
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