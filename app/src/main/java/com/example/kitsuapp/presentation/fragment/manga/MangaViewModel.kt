package com.example.kitsuapp.presentation.fragment.manga

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Data
import com.example.domain.use_cases.GetMangaUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class MangaViewModel(
    private val getMangaUseCase: GetMangaUseCase
): BaseViewModel() {

    val mangaFlow: Flow<PagingData<Data>>
    private val searchBy = MutableStateFlow("")

    init {
        mangaFlow = searchBy.flatMapLatest { text ->
            if (text.isBlank()) {
                getMangaUseCase(null)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            } else {
                getMangaUseCase.invoke(text)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            }
        }
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}