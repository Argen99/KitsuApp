package com.example.kitsuapp.presentation.fragment.main_flow.main.manga

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.core.extension.showToast
import com.example.kitsuapp.databinding.BsFilterBinding
import com.example.kitsuapp.databinding.FragmentMangaBinding
import com.example.kitsuapp.model.CategoriesDataUI
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.AnimePagingAdapter
import com.example.kitsuapp.presentation.adapter.CategoriesAdapter
import com.example.kitsuapp.presentation.adapter.DefaultLoadStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MangaFragment : BaseFragment<FragmentMangaBinding, MangaViewModel>(R.layout.fragment_manga) {
    override val binding by viewBinding(FragmentMangaBinding::bind)
    override val viewModel by viewModel<MangaViewModel>()

    private val categoriesList = arrayListOf<CategoriesDataUI>()

    private val mangaAdapter: AnimePagingAdapter by lazy {
        AnimePagingAdapter(this::onItemClick)
    }

    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(categoriesList)
    }

    override fun initialize() {
        binding.rvManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mangaAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }

        mangaAdapter.addLoadStateListener { state ->
            binding.pbManga.isVisible = state.source.refresh is LoadState.Loading
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

        viewModel.getCategoriesState.spectateUiState(
            success = { data -> categoriesAdapter.submitData(data) },
            error = { showToast(it) }
        )
    }

    override fun setupListeners() {
        binding.btnFilterManga.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        val filerBinding = BsFilterBinding.inflate(layoutInflater)
        val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)

        filerBinding.rvCategoriesAnime.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter
        }

        filerBinding.btnApply.setOnClickListener {
            viewModel.filterBy(categoriesAdapter.getSelectedItems())
            bottomSheet.dismiss()
        }

        filerBinding.btnReset.setOnClickListener {
            viewModel.filterBy(emptyList())
            categoriesAdapter.clearSelectedItems()
            bottomSheet.dismiss()
        }

        bottomSheet.setContentView(filerBinding.root)
        bottomSheet.show()
    }

    private fun onItemClick(id: String) {

    }
}