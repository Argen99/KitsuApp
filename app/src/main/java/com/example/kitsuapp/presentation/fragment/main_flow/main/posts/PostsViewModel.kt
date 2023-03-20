package com.example.kitsuapp.presentation.fragment.main_flow.main.posts

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.LoginResponse
import com.example.domain.model.PostsData
import com.example.domain.model.User
import com.example.domain.model.UserResponse
import com.example.domain.use_cases.GetPostsUseCase
import com.example.domain.use_cases.GetUserByPostIdUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.core.ui_state.UIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.PrimitiveIterator

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val getUserByPostIdUseCase: GetUserByPostIdUseCase
): BaseViewModel() {

    suspend fun getUser(id: String): User {
        return getUserByPostIdUseCase(id)
    }
    val postsFlow: Flow<PagingData<PostsData>> = getPostsUseCase().cachedIn(viewModelScope)
}