package com.example.data.remote.api_service

import com.example.data.remote.model.LoginRequestDto
import com.example.data.remote.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST
/**
 * [AuthApiService] Api Service для работы с авторизацией пользователя
 */
interface AuthApiService {

    @POST("api/oauth/token")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): LoginResponseDto
}