package com.example.kitsuapp.presentation.fragment.login

import androidx.core.view.isGone
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.local.prefs.UserDataPrefs
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.showToast
import com.example.kitsuapp.core.utils.Constants
import com.example.kitsuapp.databinding.FragmentLoginBinding
import com.example.kitsuapp.model.LoginRequestUI
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val binding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel by viewModel<LoginViewModel>()

    private val userDataPrefs: UserDataPrefs by inject()

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
            val loginRequest =
                LoginRequestUI(
                    Constants.GRANT_TYPE,
                    username = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            viewModel.login(loginRequest)
        }
    }

    override fun setupObservers() {
        viewModel.getLoginState.collectState(
            onLoading = { binding.pbLogin.isVisible = true },
            onSuccess = { onSuccessLogin() },
            onError = {
                showToast(getString(R.string.wrong_username)); binding.pbLogin.isGone = true
            }
        )
    }

    private fun onSuccessLogin() {
        showToast(getString(R.string.success))
        userDataPrefs.saveCurrentUser(true)
        binding.pbLogin.isGone = true
        navigate(R.id.mainFragment)
    }
}