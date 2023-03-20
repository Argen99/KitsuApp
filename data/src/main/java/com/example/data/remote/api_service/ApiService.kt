package com.example.data.remote.api_service

import com.example.data.remote.model.*
import retrofit2.http.*

interface ApiService {
    @GET("api/edge/anime")
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ): AnimeResponseDto

    @GET("api/edge/manga")
    suspend fun getManga(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") text: String?,
        @Query("filter[categories]") categories: List<String>?
    ): AnimeResponseDto

    @GET("api/edge/users")
    suspend fun getUsers(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[name]") name: String?
    ): UsersResponseDto

    @GET("api/edge/categories")
    suspend fun getCategories(
        @Query("page[limit]") limit: Int
    ): CategoriesResponseDto

    @POST("api/oauth/token")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): LoginResponseDto

    @GET("api/edge/posts")
    suspend fun getPosts(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ): PostsResponseDto

    @GET("api/edge/posts/{id}/user")
    suspend fun getUserByPostId(
        @Path("id") postId: String
    ): UserResponseDto

    @GET("api/edge/users")
    suspend fun getUsersByName(
        @Query("filter[name]") name: String?
    ): UsersResponseDto

    @Headers("Content-Type: application/vnd.api+json")
    @POST("api/edge/posts")
    suspend fun createPost(
        @Body body: CreatePostDto?
    )
}