package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.common.Resource
import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAnime(text: String?, categories: List<String>?): Flow<PagingData<Data>>
    fun getManga(text: String?, categories: List<String>?): Flow<PagingData<Data>>
    fun getUsers(name: String?): Flow<PagingData<User>>

    fun getUsersByName(name: String?): Flow<Resource<List<User>>>
    fun getCategories(): Flow<Resource<List<CategoriesData>>>

    fun login(username: String, password: String): Flow<Resource<LoginResponse>>

    fun getPosts(): Flow<PagingData<PostsData>>
    suspend fun getUserByPostId(id: String): User

    fun createPost(userId: String, content: String, nsfw: Boolean, spoiler: Boolean): Flow<Resource<Unit>>
}