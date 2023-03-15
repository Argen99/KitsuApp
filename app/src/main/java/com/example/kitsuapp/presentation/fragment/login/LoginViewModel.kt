package com.example.kitsuapp.presentation.fragment.login

import com.example.domain.model.LoginResponse
import com.example.domain.use_cases.LoginUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.core.ui_state.UIState
import com.example.kitsuapp.model.LoginRequestUI
import com.example.kitsuapp.model.mappers.toModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _getLoginState = MutableStateFlow<UIState<LoginResponse>>(UIState.Empty())
    val getLoginState = _getLoginState.asStateFlow()

    fun login(loginRequest: LoginRequestUI) {
        loginUseCase(loginRequest.toModel()).collectFlow(_getLoginState)
    }
}