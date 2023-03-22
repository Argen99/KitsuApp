package com.example.kitsuapp.presentation.fragment.main_flow.main.manga

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Data
import com.example.domain.use_cases.GetCategoriesUseCase
import com.example.domain.use_cases.GetMangaUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.model.CategoriesDataUI
import com.example.kitsuapp.model.mappers.toUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class MangaViewModel(
    private val getMangaUseCase: GetMangaUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel() {

    val mangaFlow: Flow<PagingData<Data>>
    private val searchBy = MutableStateFlow("")
    private val filterBy = MutableStateFlow<List<String>>(emptyList())

    private val _getCategoriesState = mutableUiStateFlow<List<CategoriesDataUI>>()
    val getCategoriesState = _getCategoriesState.asStateFlow()

    init {
        mangaFlow = combine(searchBy, filterBy) { search, filter ->
            Pair(search, filter)
        }.flatMapLatest { (search, filter) ->
            if (search.isBlank()) {
                getMangaUseCase.invoke(null, filter)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            } else {
                getMangaUseCase.invoke(search, filter)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            }
        }
        getCategoriesUseCase().gatherRequest(_getCategoriesState) { data -> data.map { it.toUI() } }
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }

    fun filterBy(value: List<String>?) {
        if (this.filterBy.value == value) return
        if (value != null) {
            this.filterBy.value = value
        }
    }
}