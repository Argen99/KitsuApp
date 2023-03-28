package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.core.base.makeNetworkRequest
import com.example.data.remote.api_service.UserApiService
import com.example.data.remote.model.mappers.toModel
import com.example.data.remote.paging_src.UsersPagingSource
import com.example.domain.either.Either
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * Класс [UserRepositoryImpl] реализует интерфейс [UserRepository]. Он предоставляет методы
 * для получения пользователей и информации о них с помощью API-сервиса.
 */
class UserRepositoryImpl(
    private val apiService: UserApiService
) : UserRepository {

    /**
     * [getUsers] Получить пользователей в виде пагинации
     * @param name имя пользователя для фильтрации
     * @return [Flow] пагинированных данных пользователей
    */
    override fun getUsers(name: String?): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService = apiService, name = name)
            }
        ).flow
    }
    /**
     * [getUsersByName] Получить список пользователей с указанным именем
     * @param name имя пользователя для поиска
     * @return [Flow] списка пользователей или ошибки
     */
    override fun getUsersByName(name: String?): Flow<Either<String, List<User>>> = makeNetworkRequest {
        apiService.getUsersByName(name).toModel().data
    }

    /**
     * [getUserByPostId] Получить пользователя по идентификатору поста
     * @param id идентификатор поста
     * @return пользователь или ошибка
     */
    override suspend fun getUserByPostId(id: String): User {
        return apiService.getUserByPostId(id).toModel().data
    }
}