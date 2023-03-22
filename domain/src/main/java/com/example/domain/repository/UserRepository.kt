package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.either.Either
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(name: String?): Flow<PagingData<User>>
    fun getUsersByName(name: String?): Flow<Either<String,List<User>>>
    suspend fun getUserByPostId(id: String): User
}