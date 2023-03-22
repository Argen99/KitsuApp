package com.example.kitsuapp.presentation.fragment.sign_flow.login

import com.example.domain.use_cases.LoginUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.model.LoginResponseUI
import com.example.kitsuapp.model.mappers.toUI
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _getLoginState = mutableUiStateFlow<LoginResponseUI>()
    val getLoginState = _getLoginState.asStateFlow()

    fun login(username: String, password: String) {
        loginUseCase(
            username = username,
            password = password
        ).gatherRequest(_getLoginState) { it.toUI() }
    }
}