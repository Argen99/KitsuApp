package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.common.Resource
import com.example.domain.model.CategoriesData
import com.example.domain.model.Data
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAnime(text: String?, categories: List<String>?): Flow<PagingData<Data>>
    fun getManga(text: String?, categories: List<String>?): Flow<PagingData<Data>>
    fun getUsers(text: String): Flow<PagingData<User>>
    fun getCategories(): Flow<Resource<List<CategoriesData>>>
}