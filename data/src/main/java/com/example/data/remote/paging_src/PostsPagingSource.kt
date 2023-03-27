package com.example.data.remote.paging_src

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_service.MangaApiService
import com.example.data.remote.api_service.PostApiService
import com.example.data.remote.model.mappers.toModel
import com.example.domain.model.PostsData
/**
 * Класс [PostsPagingSource] реализует PagingSource, который является источником данных для
 * PagingData. Он загружает страницы данных из удаленного сервера и конвертирует их в Data объекты.
 * Конструктор класса PostsPagingSource принимает [PostApiService]
 */
class PostsPagingSource(
    private val apiService: PostApiService,
) : PagingSource<Int, PostsData>() {
    /**
     * Метод [load] загружает данные для запрошенной страницы и возвращает LoadResult, который содержит
     * данные для этой страницы, предыдущий ключ и следующий ключ. Если при загрузке произошла ошибка,
     * он возвращает LoadResult.Error.
     */
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
    /**
     * Метод [getRefreshKey] возвращает ключ для обновления данных. Он используется, когда пользователь
     * перезагружает список данных, чтобы обновить его. Возвращает ключ последней загруженной страницы.
     */
    override fun getRefreshKey(state: PagingState<Int, PostsData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}