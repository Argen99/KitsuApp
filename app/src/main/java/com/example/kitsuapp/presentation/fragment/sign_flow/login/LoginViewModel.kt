package com.example.kitsuapp.presentation.fragment.sign_flow.login

import com.example.domain.model.LoginResponse
import com.example.domain.use_cases.LoginUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _getLoginState = MutableStateFlow<UIState<LoginResponse>>(UIState.Empty())
    val getLoginState = _getLoginState.asStateFlow()

    fun login(username: String, password: String) {
        loginUseCase(username =  username, password =  password).collectFlow(_getLoginState)
    }
}