package com.example.data.remote.api_service

import com.example.data.remote.model.LoginRequestDto
import com.example.data.remote.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * [AuthApiService] Api Service для работы с авторизацией пользователя
 */
interface AuthApiService {
    /**
     * [login] Функция выполняет запрос на сервер API, чтобы аутентифицировать пользователя
     * и вернуть токен доступа для дальнейшей работы с API. Если запрос прошел успешно,
     * функция возвращает объект [LoginResponseDto].
     * [loginRequestDto] - тело запроса в виде объекта [LoginRequestDto].
     */
    @POST("api/oauth/token")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): LoginResponseDto
}