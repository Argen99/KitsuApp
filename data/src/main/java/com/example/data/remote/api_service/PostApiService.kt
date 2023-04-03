package com.example.data.remote.api_service

import com.example.data.remote.model.CreatePostDto
import com.example.data.remote.model.PostsResponseDto
import retrofit2.http.*

/**
 * [PostApiService] Api Service для работы с постами
 */
interface PostApiService {

    /**
     * Функция [getPosts] выполняет GET-запрос на получение списка постов с сервера.
     * Параметры функции:
     * [limit] - количество элементов, которое нужно получить
     * [offset] - смещение, начиная с которого нужно получить элементы (нумерация начинается с 0)
     * [sort] - поле, по которому нужно отсортировать полученные элементы. По умолчанию сортировка происходит по полю createdAt.
     * Возвращаемое значение:
     * [PostsResponseDto] - объект, содержащий список постов.
     */
    @GET("api/edge/posts")
    suspend fun getPosts(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("sort") sort: String = "createdAt"
    ): PostsResponseDto

    /**
     * Функция [createPost] выполняет POST-запрос на создание нового поста на сервере.
     * Параметры функции:
     * [body] - тело запроса в виде объекта [CreatePostDto].
     * Возвращаемое значение отсутствует.
     */
    @Headers("Content-Type: application/vnd.api+json")
    @POST("api/edge/posts")
    suspend fun createPost(
        @Body body: CreatePostDto?
    )
}