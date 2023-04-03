package com.example.data.remote.repository

import com.example.data.core.base.makeNetworkRequest
import com.example.data.remote.api_service.AuthApiService
import com.example.data.remote.model.LoginRequestDto
import com.example.data.remote.model.mappers.toModel
import com.example.domain.either.Either
import com.example.domain.model.LoginResponse
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

/**
 * Класс [AuthRepositoryImpl] является реализацией интерфейса [AuthRepository].
 * Он использует сервис API [AuthApiService] для выполнения авторизации пользователя.
 */
class AuthRepositoryImpl(
    private val apiService: AuthApiService
) : AuthRepository {

    /**
     * Метод [login] отправляет запрос на сервер
     * для авторизации пользователя с заданным логином и паролем. Он возвращает
     * объект Flow<Either<String, LoginResponse>>, который содержит результат
     * выполнения операции авторизации.
     */
    override fun login(username: String, password: String): Flow<Either<String, LoginResponse>> =
        makeNetworkRequest {
            apiService.login(LoginRequestDto(username = username, password = password)).toModel()
        }
}