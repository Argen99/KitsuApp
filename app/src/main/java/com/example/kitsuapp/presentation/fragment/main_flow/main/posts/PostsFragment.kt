package com.example.kitsuapp.presentation.fragment.main_flow.main.posts

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.showToast
import com.example.kitsuapp.databinding.FragmentPostsBinding
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.DefaultLoadStateAdapter
import com.example.kitsuapp.presentation.adapter.PostsPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [PostsFragment] Фрагмент для чтения постов пользователей
 * @author Argen
 * @since 1.0v
 */
class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(R.layout.fragment_posts) {
    override val binding by viewBinding(FragmentPostsBinding::bind)
    override val viewModel by viewModel<PostsViewModel>()

    private val postsAdapter: PostsPagingAdapter by lazy {
        PostsPagingAdapter(this::onItemClick)
    }

    override fun initialize() {
        constructRecycler()

        postsAdapter.addLoadStateListener { state ->
            binding.pbPosts.isVisible = state.source.refresh is LoadState.Loading
        }
    }

    override fun setupObservers() {
        subscribeToPosts()
    }

    private fun constructRecycler() {
        binding.rvPosts.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }
    }

    /**
     * [subscribeToPosts] Так как с бэка не приходит пользователь
     * опубликовавший пост, при маппинге через id поста происходит запрос на пользователя
     * и данные записываются в специальную пустую модельку и передаются в адаптер
     */
    private fun subscribeToPosts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.postsFlow.collectLatest { pagingData ->
                postsAdapter.submitData(pagingData.map {
                    val user = viewModel.getUser(it.id!!)
                    it.relationships!!.userModel = user
                    it.toUI()
                })
            }
        }
    }

    private fun onItemClick(id: String) {
        showToast(id)
    }
}