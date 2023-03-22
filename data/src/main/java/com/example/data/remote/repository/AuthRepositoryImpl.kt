package com.example.data.remote.repository

import com.example.data.core.base.BaseRepository
import com.example.data.remote.api_service.AuthApiService
import com.example.data.remote.model.LoginRequestDto
import com.example.data.remote.model.mappers.toModel
import com.example.domain.either.Either
import com.example.domain.model.LoginResponse
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val apiService: AuthApiService
) : BaseRepository(), AuthRepository {

    override fun login(username: String, password: String): Flow<Either<String ,LoginResponse>> =
        makeNetworkRequest {
            apiService.login(LoginRequestDto(username = username, password = password)).toModel()
        }
}