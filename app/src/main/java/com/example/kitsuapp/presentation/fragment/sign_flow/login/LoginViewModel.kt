package com.example.kitsuapp.presentation.fragment.sign_flow.login

import com.example.domain.use_cases.GetAnimeUseCase
import com.example.domain.use_cases.GetCategoriesUseCase
import com.example.domain.use_cases.LoginUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.model.LoginResponseUI
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.fragment.main_flow.main.anime.AnimeFragment
import com.example.kitsuapp.presentation.fragment.main_flow.main.anime.AnimeViewModel
import kotlinx.coroutines.flow.asStateFlow
/**
 * Класс [LoginViewModel] представляет viewModel для [LoginFragment]. Он принимает
 * два UseCase-класса: [GetAnimeUseCase] для получения списка аниме и [GetCategoriesUseCase]
 * для получения списка категорий.
 */
class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    /**
     * Открытое состояние [getLoginState] является StateFlow, который является неизменяемым
     * и предоставляет только чтение текущего состояния выполнения запроса на вход в систему.
     */
    private val _getLoginState = mutableUiStateFlow<LoginResponseUI>()
    val getLoginState = _getLoginState.asStateFlow()
    /**
     * Функция [login] используется входа в приложение.
     * Она принимает username и password в качестве аргумента,
     * затем вызывает [loginUseCase], передавая ему имя пользователя,
     * чтобы получить данные пользователя, которые затем преобразуются в UI-модель
     * и передаются в [gatherRequest]. [gatherRequest] используется для получения
     * результата операции из [loginUseCase] и передачи данных в [_getLoginState].
     */
    fun login(username: String, password: String) {
        loginUseCase(
            username = username,
            password = password
        ).gatherRequest(_getLoginState) { it.toUI() }
    }
}