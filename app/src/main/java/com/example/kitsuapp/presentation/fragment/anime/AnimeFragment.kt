package com.example.kitsuapp.presentation.fragment.anime

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.databinding.FragmentAnimeBinding
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.AnimePagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimeFragment : BaseFragment<FragmentAnimeBinding,AnimeViewModel>(R.layout.fragment_anime) {
    override val binding by viewBinding(FragmentAnimeBinding::bind)
    override val viewModel by viewModel<AnimeViewModel>()

    private val animeAdapter: AnimePagingAdapter by lazy {
        AnimePagingAdapter(this::onItemClick)
    }

    override fun initialize() {
        binding.rvAnime.apply {
            layoutManager =  GridLayoutManager(requireContext(), 2)
            adapter = animeAdapter
        }
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.animeFlow.collectLatest { pagingData ->
                animeAdapter.submitData(pagingData.map { it.toUI() })
            }
        }

        binding.etSearchAnime.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    private fun onItemClick(id: String) {

    }
}