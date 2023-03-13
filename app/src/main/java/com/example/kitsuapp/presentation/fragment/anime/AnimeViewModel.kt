package com.example.kitsuapp.presentation.fragment.anime

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Data
import com.example.domain.use_cases.GetAnimeUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class AnimeViewModel(
    private val getAnimeUseCase: GetAnimeUseCase
): BaseViewModel() {

    val animeFlow: Flow<PagingData<Data>>
    private val searchBy = MutableStateFlow("")

    init {
        animeFlow = searchBy.flatMapLatest { text ->
            if(text.isBlank()) {
                getAnimeUseCase.invoke(null)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            } else {
                getAnimeUseCase.invoke(text)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            }
        }
    }

    fun searchBy(value: String?) {
        if (searchBy.value == value) return
        if (value != null) {
            searchBy.value = value
        }
    }
}