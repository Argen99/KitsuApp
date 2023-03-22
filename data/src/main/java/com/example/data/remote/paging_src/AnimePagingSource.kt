package com.example.data.remote.paging_src

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_service.AnimeApiService
import com.example.data.remote.model.mappers.toModel
import com.example.domain.model.Data

class AnimePagingSource(
    private val apiService: AnimeApiService,
    private val text: String?,
    private val categories: List<String>?,
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val pageIndex = params.key ?: 0

        return try {
            val response = apiService.getAnime(
                limit = params.loadSize, offset = pageIndex, text = text,
                categories = categories
            ).toModel()
            LoadResult.Page(
                data = response.data,
                nextKey = if (response.data.size == params.loadSize) pageIndex + params.loadSize else null,
                prevKey = null
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}