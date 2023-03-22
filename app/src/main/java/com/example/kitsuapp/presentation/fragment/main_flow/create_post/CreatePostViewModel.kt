package com.example.kitsuapp.presentation.fragment.main_flow.create_post

import com.example.domain.use_cases.CreatePostUseCase
import com.example.domain.use_cases.GetUserByNameUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.model.UserUI
import com.example.kitsuapp.model.mappers.toUI
import kotlinx.coroutines.flow.asStateFlow

class CreatePostViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val createPostUseCase: CreatePostUseCase
) : BaseViewModel() {

    private val _getCreatePostState = mutableUiStateFlow<Unit>()
    val getCreatePostState = _getCreatePostState.asStateFlow()

    private val _userFlow = mutableUiStateFlow<List<UserUI>>()
    val userFlow = _userFlow.asStateFlow()

    fun getUser(username: String) {
        getUserByNameUseCase(username).gatherRequest(_userFlow) { data -> data.map { it.toUI() } }
    }

    fun createPost(userId: String, content: String, nsfw: Boolean, spoiler: Boolean) {
        createPostUseCase(
            userId = userId, content = content,
            nsfw = nsfw, spoiler = spoiler
        ).gatherRequest(_getCreatePostState)
    }
}