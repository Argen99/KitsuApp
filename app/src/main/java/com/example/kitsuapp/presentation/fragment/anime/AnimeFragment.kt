package com.example.kitsuapp.presentation.fragment.anime

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.local.prefs.UserDataPrefs
import com.example.kitsuapp.R
import com.example.kitsuapp.core.base.BaseFragment
import com.example.kitsuapp.databinding.BsFilterBinding
import com.example.kitsuapp.databinding.FragmentAnimeBinding
import com.example.kitsuapp.model.CategoriesDataUI
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.AnimePagingAdapter
import com.example.kitsuapp.presentation.adapter.CategoriesAdapter
import com.example.kitsuapp.presentation.adapter.DefaultLoadStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimeFragment : BaseFragment<FragmentAnimeBinding, AnimeViewModel>(R.layout.fragment_anime) {
    override val binding by viewBinding(FragmentAnimeBinding::bind)
    override val viewModel by viewModel<AnimeViewModel>()

    private val categoriesList = arrayListOf<CategoriesDataUI>()

    private val user: UserDataPrefs by inject()

    private val animeAdapter: AnimePagingAdapter by lazy {
        AnimePagingAdapter(this::onItemClick)
    }

    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(categoriesList)
    }

    override fun initialize() {
        binding.rvAnime.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = animeAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }
    }

    override fun setupListeners() {
        binding.btnFilterAnime.setOnClickListener {
            showBottomSheet()
        }

        binding.btn.setOnClickListener {
            user.saveCurrentUser(false)
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

        animeAdapter.addLoadStateListener { state ->
            binding.pbAnime.isVisible = state.source.refresh is LoadState.Loading
        }

        viewModel.getCategoriesState.collectState(
            onLoading = {},
            onSuccess = { data -> categoriesAdapter.submitData(data.map { it.toUI() }) },
            onError = {}
        )
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