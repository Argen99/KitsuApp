package com.example.kitsuapp.presentation.fragment.main_flow.create_post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.common.Resource
import com.example.domain.model.User
import com.example.domain.use_cases.CreatePostUseCase
import com.example.domain.use_cases.GetUserByNameUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.core.ui_state.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class CreatePostViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val createPostUseCase: CreatePostUseCase
) : BaseViewModel() {

    val userFlow: Flow<Resource<List<User>>>
    private val searchBy = MutableLiveData("")

    init {
        userFlow = searchBy.asFlow()
            .flatMapLatest {
            getUserByNameUseCase(it)
            }
    }

    fun setName(name: String?) {
        if (searchBy.value == name) return
        if (name != null) {
            searchBy.value = name
        }
    }

    private val _getCreatePostState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val getCreatePostState = _getCreatePostState.asStateFlow()

    fun createPost(userId: String, content: String, nsfw: Boolean, spoiler: Boolean) {
        createPostUseCase(
            userId = userId, content = content,
            nsfw = nsfw, spoiler = spoiler
        ).collectFlow(_getCreatePostState)
    }
}