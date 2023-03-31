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
    /**
     * [getUsers] Метод getUsers используется для получения списка пользователей.
     * Принимает следующие параметры:
     * @param limit - количество элементов на странице
     * @param offset - смещение элементов
     * @param name - имя пользователя для поиска
     */
    @GET("api/edge/users")
    suspend fun getUsers(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[name]") name: String?
    ): UsersResponseDto

    /**
     * Метод [getUserByPostId] используется для получения информации о пользователе,
     * создавшем пост с заданным идентификатором. Принимает идентификатор поста в качестве
     * параметра и возвращает объект типа UserResponseDto.
     */
    @GET("api/edge/posts/{id}/user")
    suspend fun getUserByPostId(
        @Path("id") postId: String
    ): UserResponseDto

    /**
     * Метод [getUsersByName] используется для получения списка пользователей,
     * удовлетворяющих заданным критериям. Принимает имя пользователя в качестве
     * параметра и возвращает объект типа UsersResponseDto.
     */
    @GET("api/edge/users")
    suspend fun getUsersByName(
        @Query("filter[name]") name: String?
    ): UsersResponseDto
}