package com.example.kitsuapp.presentation.fragment.main_flow.create_post

import com.example.domain.use_cases.CreatePostUseCase
import com.example.domain.use_cases.GetUserByNameUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.model.UserUI
import com.example.kitsuapp.model.mappers.toUI
import kotlinx.coroutines.flow.asStateFlow

/**
 * Класс [CreatePostViewModel] представляет модель представления для [CreatePostFragment].
 * Он содержит два конструкторных параметра: getUserByNameUseCase и createPostUseCase.
 * [getUserByNameUseCase] - use case для получения пользователя по имени,
 * [createPostUseCase] - это use case для создания поста
 */
class CreatePostViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val createPostUseCase: CreatePostUseCase
) : BaseViewModel() {

    private val _getCreatePostState = mutableUiStateFlow<Unit>()
    val getCreatePostState = _getCreatePostState.asStateFlow()

    private val _userFlow = mutableUiStateFlow<List<UserUI>>()
    val userFlow = _userFlow.asStateFlow()

    /**
     * Функция [getUser] используется для получения пользователя по его имени.
     * Она принимает имя пользователя в качестве аргумента username,
     * затем вызывает [getUserByNameUseCase], передавая ему имя пользователя,
     * чтобы получить данные пользователя, которые затем преобразуются в UI-модель
     * и передаются в [gatherRequest]. [gatherRequest] используется для получения
     * результата операции из [createPostUseCase] и передачи данных в [_getCreatePostState].
     */
    fun getUser(username: String) {
        getUserByNameUseCase(username).gatherRequest(_userFlow) { data -> data.map { it.toUI() } }
    }

    /**
      * Функция [createPost] используется для создания нового поста. Она принимает в качестве
     * параметров идентификатор пользователя userId, содержимое поста content,
     * булевые флаги nsfw и spoiler. Функция вызывает метод createPostUseCase,
     * передавая параметры создания поста. [gatherRequest] используется для получения
     * результата операции из [createPostUseCase] и передачи данных в [_getCreatePostState].
     */
    fun createPost(userId: String, content: String, nsfw: Boolean, spoiler: Boolean) {
        createPostUseCase(
            userId = userId, content = content,
            nsfw = nsfw, spoiler = spoiler
        ).gatherRequest(_getCreatePostState)
    }
}