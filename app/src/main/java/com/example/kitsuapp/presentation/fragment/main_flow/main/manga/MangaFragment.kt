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

/**
 * [MangaFragment] наследуется от базового класса [BaseFragment] который содержит общую
 * логику для фрагментов в приложении и представляет собой фрагмент,
 * отображающий список манга c возможностью фильтрации по категориям и поиска.
 * @author Argen
 * @since 1.0v
 */
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
    /**
     * [initialize] используется для инициализации элементов пользовательского интерфейса.
     */
    override fun initialize() {
        constructRecycler()

        mangaAdapter.addLoadStateListener { state ->
            binding.pbManga.isVisible = state.source.refresh is LoadState.Loading
        }
    }

    /**
     * [setupObservers] наблюдателет за данными,
     * получаемыми из ViewModel.
     * разворачивая их из [com.example.kitsuapp.core.ui_state.UIState]
     */
    override fun setupObservers() {
        subscribeToManga()
        subscribeToCategories()
        binding.etSearchManga.addTextChangedListener {
            viewModel.searchBy(it.toString())
        }
    }
    /**
     * [setupListeners] используется чтобы установить слушатели для каких-либо View или
     * других элементов пользовательского интерфейса.
     */
    override fun setupListeners() {
        binding.btnFilterManga.setOnClickListener {
            showBottomSheet()
        }
    }
    /**
     * [constructRecycler] настраивает RecyclerView с помощью LayoutManager и адаптера.
    */
    private fun constructRecycler() {
        binding.rvManga.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mangaAdapter.withLoadStateFooter(DefaultLoadStateAdapter())
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
     * [subscribeToManga] подписывается на flow и обновляет
     * [AnimePagingAdapter] при получении новых данных.
     */
    private fun subscribeToManga() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mangaFlow.collectLatest { pagingData ->
                mangaAdapter.submitData(pagingData.map { it.toUI() })
            }
        }
    }

    /**
     * [onItemClick] вызывается при клике на элемент списка манга и используется
     * для отображения Toast сообщения с id элемента.
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
     * Автоматически при создании viewmodel происходит запрос на категории и хранятся в [categoriesList]
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