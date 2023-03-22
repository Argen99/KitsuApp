package com.example.data.remote.paging_src

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_service.UserApiService
import com.example.data.remote.model.mappers.toModel
import com.example.domain.model.User

class UsersPagingSource(
    private val apiService: UserApiService,
    private val name: String?
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: 0

        return try {
            val response = apiService.getUsers(
                limit = params.loadSize, offset = pageIndex, name = name
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

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}