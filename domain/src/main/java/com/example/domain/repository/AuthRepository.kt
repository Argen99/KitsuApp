package com.example.domain.repository

import com.example.domain.either.Either
import com.example.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(username: String, password: String): Flow<Either<String, LoginResponse>>
}