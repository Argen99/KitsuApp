package com.example.kitsuapp.presentation.fragment.main_flow.main.anime

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
import com.example.kitsuapp.databinding.FragmentAnimeBinding
import com.example.kitsuapp.model.CategoriesDataUI
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.adapter.AnimePagingAdapter
import com.example.kitsuapp.presentation.adapter.CategoriesAdapter
import com.example.kitsuapp.presentation.adapter.DefaultLoadStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * [AnimeFragment] AnimeFragment наследуется от [BaseFragment], который содержит общую
 * логику для фрагментов в приложении и представляет собой фрагмент,
 * отображающий список аниме c возможностью фильтрации по категориям и поиска.
 * @author Argen
 * @since 1.0v
 */
class AnimeFragment : BaseFragment<FragmentAnimeBinding, AnimeViewModel>(R.layout.fragment_anime) {
    override val binding by viewBinding(FragmentAnimeBinding::bind)
    override val viewModel by viewModel<AnimeViewModel>()

    private val categoriesList = arrayListOf<CategoriesDataUI>()

    private val animeAdapter: AnimePagingAdapter by lazy {
        AnimePagingAdapter(this::onItemClick)
    }

    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(categoriesList)
    }

    /**
     * [initialize] используется для инициализации элементов пользовательского интерфейса.
     */
    override fun initialize() {
        constructRecycler()

        animeAdapter.addLoadStateListener { state ->
            binding.pbAnime.isVisible = state.source.refresh is LoadState.Loading
        }
    }

    /**
     * [setupListeners] используется чтобы установить слушатели для каких-либо View или
     * других элементов пользовательского интерфейса.
     */
    override fun setupListeners() {
        binding.btnFilterAnime.setOnClickListener {
            showBottomSheet()
        }
    }

    /**
     * [setupObservers] метод для наблюдания за данными,
     * получаемыми из ViewModel.
     */
    override fun setupObservers() {
        subscribeToAnime()
        subscribeToCategories()
        binding.etSearchAnime.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }

    /**
     * [constructRecycler] настраивает RecyclerView с помощью LayoutManager и адаптера.
     */
    private fun constructRecycler() {
        binding.rvAnime.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = animeAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
        }
    }

    /**
     * [subscribeToCategories] подписывается на Flow,
     * разворачивая его из [com.example.kitsuapp.core.ui_state.UIState]
     */
    private fun subscribeToCategories() {
        viewModel.getCategoriesState.spectateUiState(
            success = { data -> categoriesAdapter.submitData(data) },
            error = { showToast(it) }
        )
    }

    /**
     * [subscribeToAnime] подписывается на flow и обновляет
     * [AnimePagingAdapter] при получении новых данных.
     */
    private fun subscribeToAnime() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.animeFlow.collectLatest { pagingData ->
                animeAdapter.submitData(pagingData.map { it.toUI() })
            }
        }
    }

    /**
     * [onItemClick] вызывается при нажатии на элемент списка
     * аниме и отображает идентификатор выбранного элемента
     */
    private fun onItemClick(id: String) {
        showToast(id)
    }

    /**
     * [showBottomSheet] используется для отображения BottomSheet для фильтрации списка
     * манга по категориям. Здесь мы создаем экземпляр BsFilterBinding для настройки
     * макета BottomSheet. Затем мы настраиваем RecyclerView для отображения списка категорий
     * и устанавливаем слушатели для кнопок "Применить" и "Сбросить". После этого мы
     * устанавливаем макет BsFilterBinding в качестве содержимого BottomSheet и отображаем его.
     * Автоматически при инициализации viewmodel происходит запрос на категории
     * и хранятся в [categoriesList]
     */
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
}