package com.example.kitsuapp.presentation.fragment.sign_flow.login

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.local.prefs.TokenManager
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.*
import com.example.kitsuapp.databinding.FragmentLoginBinding
import com.example.kitsuapp.model.LoginResponseUI
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [LoginFragment] Фрагмент для авторизации пользователя
 * @author Argen
 * @since 1.0v
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val binding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel by viewModel<LoginViewModel>()

    private val tokenManager: TokenManager by inject()

    override fun initialize() {
        if (!tokenManager.onBoardIsShown) {
            findNavController().navigateSafely(R.id.action_loginFragment_to_boardFragment)
        }
    }

    /**
     * [setupListeners] используется чтобы установить слушатели для каких-либо View или
     * других элементов пользовательского интерфейса.
     */
    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    /**
     * [setupObservers] метод для наблюдания за данными,
     * получаемыми из ViewModel.
     */
    override fun setupObservers() {
        subscribeToLoginState()
    }

    /**
     * [login] Проверяет введенные данные и запускает процесс входа
     */
    private fun login() = with(binding) {
        if (etEmail.text.isNullOrBlank()) {
            etEmail.error = getString(R.string.enter_email)
        } else if (etPassword.text.isNullOrBlank()) {
            binding.etPassword.error = getString(R.string.enter_passwors)
        } else {
            viewModel.login(
                username = etEmail.text.toString(),
                password = etPassword.text.toString()
            )
        }
    }

    /**
     * [subscribeToLoginState] наблюдает за Flow,
     * разворачивая его из [com.example.kitsuapp.core.ui_state.UIState]
     */
    private fun subscribeToLoginState() {
        viewModel.getLoginState.spectateUiState(
            loading = {
                binding.pbLogin.visible()
            },
            success = {
                onSuccessLogin(it)
            },
            error = {
                binding.pbLogin.gone()
                showToast(getString(R.string.wrong_username))
            }
        )
    }

    /**
     * Обрабатывает успешный вход в приложение
     */
    private fun onSuccessLogin(loginResponse: LoginResponseUI) {
        showToast(getString(R.string.success))
        binding.pbLogin.gone()
        tokenManager.accessToken = loginResponse.access_token
        tokenManager.refreshToken = loginResponse.refresh_token
        activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
    }
}