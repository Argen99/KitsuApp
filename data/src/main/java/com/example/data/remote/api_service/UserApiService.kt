package com.example.data.remote.api_service

import com.example.data.remote.model.UserResponseDto
import com.example.data.remote.model.UsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * [UserApiService] Api Service для работы с пользователями
 */
interface UserApiService {

    @GET("api/edge/users")
    suspend fun getUsers(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[name]") name: String?
    ): UsersResponseDto

    @GET("api/edge/posts/{id}/user")
    suspend fun getUserByPostId(
        @Path("id") postId: String
    ): UserResponseDto

    @GET("api/edge/users")
    suspend fun getUsersByName(
        @Query("filter[name]") name: String?
    ): UsersResponseDto
}