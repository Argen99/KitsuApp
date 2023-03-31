package com.example.kitsuapp.presentation.fragment.main_flow.main.anime

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.CategoriesData
import com.example.domain.model.Data
import com.example.domain.use_cases.GetAnimeUseCase
import com.example.domain.use_cases.GetCategoriesUseCase
import com.example.kitsuapp.core.base.BaseViewModel
import com.example.kitsuapp.core.ui_state.UIState
import com.example.kitsuapp.model.CategoriesDataUI
import com.example.kitsuapp.model.mappers.toUI
import com.example.kitsuapp.presentation.fragment.main_flow.main.manga.MangaViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
/**
 * Класс [AnimeViewModel] представляет viewModel для [AnimeFragment]. Он принимает
  * два UseCase-класса: [GetAnimeUseCase] для получения списка аниме и [GetCategoriesUseCase]
  * для получения списка категорий.
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class AnimeViewModel(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel() {

    /**
     * AnimeViewModel имеет два MutableStateFlow поля: [searchByS] и [filterByS], которые используются
      * для поиска и фильтрации аниме соответственно. Эти поля изменяются через
      * методы [searchBy] и [filterBy].
     * Кроме того, класс имеет Flow<PagingData<Data>> поле [animeFlow], которое используется
      * для передачи списка аниме в пользовательский интерфейс. При изменении поля searchBy
      * или filterBy animeFlow перезапрашивается с помощью метода getAnimeUseCase.invoke().
     */
    val animeFlow: Flow<PagingData<Data>>
    private val searchByS = MutableStateFlow("")
    private val filterByS = MutableStateFlow<List<String>>(emptyList())

    /**
     * Класс также имеет поле [getCategoriesState], которое содержит список категорий аниме,
      * полученный с помощью метода [getCategoriesUseCase].
     */
    private val _getCategoriesState = mutableUiStateFlow<List<CategoriesDataUI>>()
    val getCategoriesState = _getCategoriesState.asStateFlow()

    /**
     * В блоке инициализации класса [AnimeViewModel] происходит инициализация полей [animeFlow]
     * и [getCategoriesState].
     * [animeFlow] использует метод [combine] для комбинации двух MutableStateFlow полей:
     * [searchByS] и [filterByS].Затем метод [flatMapLatest] вызывается на результате combine(),
     * чтобы получить новый Flow,который содержит список manga. Если поле [searchByS] пустое,
     * то вызывается метод getAnimeUseCase.invoke() с параметром null так как если отправить
     * пустой аргумент для фильтрации приходит пустой список, иначе вызывается
     * с параметром [searchByS]. Результат вызова метода [debounce] используется для
     * предотвращения отправки слишком частых запросов при поиске если пользователь
     * быстро печатает, а cachedIn(viewModelScope)
     * используется для сохранения результата запроса в кэше.
     */
    /**
     * [getCategoriesState] использует метод [gatherRequest] для вызова метода getCategoriesUseCase()
     * и получения списка категорий. Результат вызова метода map() используется для преобразования
     * полученных данных в модельку для UI слоя. Преобразованный список передается в
     * _getCategoriesState, который также используется для
     * передачи списка в пользовательский интерфейс.
     */
    init {
        animeFlow = combine(searchByS, filterByS) { search, filter ->
            Pair(search, filter)
        }.flatMapLatest { (search, filter) ->
            if (search.isBlank()) {
                getAnimeUseCase.invoke(null, filter)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            } else {
                getAnimeUseCase.invoke(search, filter)
                    .debounce(500)
                    .cachedIn(viewModelScope)
            }
        }
        getCategoriesUseCase().gatherRequest(_getCategoriesState) {data -> data.map { it.toUI() }}
    }
    /**
     * [searchBy] Метод для передачи аргументов поиска
     */
    fun searchBy(value: String?) {
        if (searchByS.value == value) return
        if (value != null) {
            searchByS.value = value
        }
    }
    /**
     * [searchBy] Метод для передачи аргументов поиска.
     */
    fun filterBy(value: List<String>?) {
        if (this.filterByS.value == value) return
        if (value != null) {
            this.filterByS.value = value
        }
    }
}




