package com.example.data.remote.paging_src

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_service.ApiService
import com.example.data.remote.model.mappers.toModel
import com.example.domain.model.Data
import com.example.domain.model.PostsData

class PostsPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, PostsData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostsData> {
        val pageIndex = params.key ?: 0

        return try {
            val response = apiService.getPosts(
                    limit = params.loadSize, pageIndex
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

    override fun getRefreshKey(state: PagingState<Int, PostsData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}