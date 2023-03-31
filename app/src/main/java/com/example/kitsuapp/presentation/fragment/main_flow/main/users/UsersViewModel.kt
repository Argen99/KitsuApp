package com.example.kitsuapp.presentation.fragment.main_flow.main.users

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.User
import com.example.domain.use_cases.GetUsersUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

/**
 * Класс [UsersViewModel] представляет viewModel для [UsersFragment], который содержит логику для получения
 * списка пользователей. Он также наследует класс [BaseViewModel].
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {

    /**
     * [usersFlow] Возвращает экземпляр Flow<PagingData<User>>, который представляет список
     * пользователей с возможностью постраничной загрузки.
     */
    val usersFlow: Flow<PagingData<User>>

    /**
     * [searchByS] возвращает экземпляр MutableStateFlow<String>, который хранит значение для
     * поиска пользователей.
     */
    private val searchByS = MutableStateFlow("")

    /**
     * [init] блок инициализации [UsersViewModel] Инициализирует usersFlow с помощью flatMapLatest,
     * который отслеживает изменения в searchBy и отправляет запрос на получение пользователей
     * в зависимости от того, является ли строка поиска пустой или нет.
     */
    init {
        usersFlow = searchByS.flatMapLatest { name ->
            if (name.isBlank()) {
                getUsersUseCase(null)
                    .cachedIn(viewModelScope)
            } else {
                getUsersUseCase(name)
                    .cachedIn(viewModelScope)
            }
        }
    }

    /**
     * Устанавливает новое значение для [searchByS], если оно отличается от текущего значения.
     */
    fun searchBy(value: String) {
        if (searchByS.value == value) return
        searchByS.value = value
    }
}