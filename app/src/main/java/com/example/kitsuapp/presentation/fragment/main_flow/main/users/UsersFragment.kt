package com.example.kitsuapp.presentation.fragment.main_flow.main.users

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.showToast
import com.example.kitsuapp.databinding.FragmentUsersBinding
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.DefaultLoadStateAdapter
import com.example.kitsuapp.presentation.adapter.UsersPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [UsersFragment] Фрагмент для поиска пользователей
 * @author Argen
 * @since 1.0v
 */
class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(R.layout.fragment_users) {
    override val binding by viewBinding(FragmentUsersBinding::bind)
    override val viewModel by viewModel<UsersViewModel>()

    private val usersAdapter: UsersPagingAdapter by lazy {
        UsersPagingAdapter(this::onItemClick)
    }

    override fun initialize() {
        constructRecycler()

        usersAdapter.addLoadStateListener { state ->
            binding.pbUsers.isVisible = state.source.refresh is LoadState.Loading
        }
    }

    override fun setupObservers() {
        subscribeToUsers()
    }

    override fun setupListeners() {
        binding.etSearchUser.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    private fun constructRecycler() {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = usersAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }
    }

    private fun subscribeToUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usersFlow.collectLatest { pagingData ->
                usersAdapter.submitData(pagingData.map { it.toUI() })
            }
        }
    }

    private fun onItemClick(id: String) {
        showToast(id)
    }
}
