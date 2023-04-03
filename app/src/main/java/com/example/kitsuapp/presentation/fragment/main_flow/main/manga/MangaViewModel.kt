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

/**
 * Класс [MangaViewModel] представляет viewModel для  [MangaFragment]. Он принимает
 * два UseCase-класса: [getMangaUseCase] для получения списка манга и [getCategoriesUseCase]
 * для получения списка категорий.
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class MangaViewModel(
    private val getMangaUseCase: GetMangaUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel() {

    /**
     * [MangaViewModel] имеет два MutableStateFlow поля: [searchByS] и [filterByS], которые используются
     * для поиска и фильтрации аниме соответственно. Эти поля изменяются через
     * функции [searchBy] и [filterBy].
     * Кроме того, класс имеет Flow<PagingData<Data>> поле [mangaFlow], которое используется
     * для передачи списка аниме в пользовательский интерфейс. При изменении поля searchBy
     * или filterBy animeFlow перезапрашивается с помощью метода getAnimeUseCase.invoke().
     */
    val mangaFlow: Flow<PagingData<Data>>
    private val searchByS = MutableStateFlow("")
    private val filterByS = MutableStateFlow<List<String>>(emptyList())

    /**
     * Класс также имеет поле [getCategoriesState], которое содержит список категорий аниме,
     * полученный с помощью метода [getCategoriesUseCase].
     */
    private val _getCategoriesState = mutableUiStateFlow<List<CategoriesDataUI>>()
    val getCategoriesState = _getCategoriesState.asStateFlow()

    /**
     * В блоке инициализации класса [MangaViewModel] происходит инициализация полей [mangaFlow]
     * и [getCategoriesState].
     * [mangaFlow] использует метод [combine] для комбинации двух MutableStateFlow полей:
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
     * getCategoriesState использует метод gatherRequest() для вызова метода getCategoriesUseCase()
     * и получения списка категорий. Результат вызова метода map() используется для преобразования
     * полученных данных в модельку для UI слоя. Преобразованный список передается в
     * [_getCategoriesState], который также используется для
     * передачи списка в пользовательский интерфейс.
     */
    init {
        mangaFlow = combine(searchByS, filterByS) { search, filter ->
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

    /**
     * [searchBy] Метод для передачи аргументов поиска
     */
    fun searchBy(value: String) {
        if (searchByS.value == value) return
        searchByS.value = value
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