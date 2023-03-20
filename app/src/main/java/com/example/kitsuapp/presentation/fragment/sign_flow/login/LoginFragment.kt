package com.example.kitsuapp.presentation.fragment.sign_flow.login

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.local.prefs.TokenManager
import com.example.domain.model.LoginResponse
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.activityNavController
import com.example.kitsuapp.core.extension.gone
import com.example.kitsuapp.core.extension.navigateSafely
import com.example.kitsuapp.core.extension.showToast
import com.example.kitsuapp.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val binding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel by viewModel<LoginViewModel>()

    private val tokenManager: TokenManager by inject()

    override fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

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

    override fun setupObservers() {
        viewModel.getLoginState.collectState(
            onLoading = { binding.pbLogin.isVisible = true },
            onSuccess = { onSuccessLogin(it) },
            onError = {
                showToast(getString(R.string.wrong_username)); binding.pbLogin.isGone = true
            }
        )
    }

    private fun onSuccessLogin(loginResponse: LoginResponse) {
        showToast(getString(R.string.success))
        binding.pbLogin.gone()
        tokenManager.saveAccessToken(loginResponse.access_token)
        tokenManager.saveRefreshToken(loginResponse.refresh_token)
        activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
    }
}