package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.makeNetworkRequest
import com.example.data.remote.api_service.PostApiService
import com.example.data.remote.model.*
import com.example.data.remote.paging_src.PostsPagingSource
import com.example.domain.either.Either
import com.example.domain.model.PostsData
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

/**
 * [PostRepositoryImpl] Класс PostRepositoryImpl является реализацией интерфейса [PostRepository].
 * Он использует сервис API [PostApiService] для получения данных и отправки запросов.
 */
class PostRepositoryImpl(
    private val apiService: PostApiService
) : PostRepository {
    /**
     * Метод [getPosts] возвращает объект Flow<PagingData<PostsData>> для постраничного получения
     * данных о постах. Он использует объект Pager для настройки параметров постраничного получения
     * и pagingSourceFactory для создания объекта PostsPagingSource, который реализует
     * загрузку страниц данных.
     */
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

    /**
     * Метод [createPost] отправляет запрос на создание нового поста с заданным содержанием, флагами
     * NSFW и SPOILER и идентификатором пользователя. Он возвращает объект Flow<Either<String, Unit>>,
     * который содержит результат выполнения операции создания поста.
     */
    override fun createPost(
        userId: String,
        content: String,
        nsfw: Boolean,
        spoiler: Boolean
    ): Flow<Either<String, Unit>> = makeNetworkRequest {
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