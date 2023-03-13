package com.example.kitsuapp.presentation.fragment.users

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Data
import com.example.domain.model.User
import com.example.domain.use_cases.GetUsersUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
): BaseViewModel() {

    val usersFlow: Flow<PagingData<User>>
    private val searchBy = MutableStateFlow("")

    init {
        usersFlow = searchBy.flatMapLatest {
            getUsersUseCase(it)
                .cachedIn(viewModelScope)
        }
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}