package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.either.Either
import com.example.domain.model.PostsData
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<PagingData<PostsData>>
    fun createPost(
        userId: String,
        content: String,
        nsfw: Boolean,
        spoiler: Boolean
    ): Flow<Either<String, Unit>>
}