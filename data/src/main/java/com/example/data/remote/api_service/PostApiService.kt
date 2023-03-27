package com.example.data.remote.api_service

import com.example.data.remote.model.CreatePostDto
import com.example.data.remote.model.PostsResponseDto
import retrofit2.http.*

interface PostApiService {

    @GET("api/edge/posts")
    suspend fun getPosts(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("sort") sort: String = "createdAt"
    ): PostsResponseDto

    @Headers("Content-Type: application/vnd.api+json")
    @POST("api/edge/posts")
    suspend fun createPost(
        @Body body: CreatePostDto?
    )
}