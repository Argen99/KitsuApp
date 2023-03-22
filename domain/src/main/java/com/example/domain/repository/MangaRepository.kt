package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    fun getManga(text: String?, categories: List<String>?): Flow<PagingData<Data>>
}