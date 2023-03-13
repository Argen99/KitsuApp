package com.example.kitsuapp.presentation.fragment.manga

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.databinding.FragmentMangaBinding
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.AnimePagingAdapter
import com.example.kitsuapp.presentation.adapter.DefaultLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaFragment : BaseFragment<FragmentMangaBinding, MangaViewModel>(R.layout.fragment_manga) {
    override val binding by viewBinding(FragmentMangaBinding::bind)
    override val viewModel by viewModel<MangaViewModel>()

    private val mangaAdapter: AnimePagingAdapter by lazy {
        AnimePagingAdapter(this::onItemClick)
    }

    override fun initialize() {
        binding.rvManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mangaAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mangaFlow.collectLatest { pagingData ->
                mangaAdapter.submitData(pagingData.map { it.toUI() })
            }
        }

        binding.etSearchManga.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }

        mangaAdapter.addLoadStateListener { state ->
            binding.pbManga.isVisible = state.source.refresh is LoadState.Loading
        }
    }

    private fun onItemClick(id: String) {

    }
}