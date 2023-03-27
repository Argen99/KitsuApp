package com.example.kitsuapp.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.either.Either
import com.example.kitsuapp.core.ui_state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * [BaseViewModel] Класс BaseViewModel - это абстрактный класс, который является базовым
 * для всех ViewModel в приложении. Он наследует класс ViewModel.
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * [mutableUiStateFlow] mutableUiStateFlow() - функция, которая возвращает MutableStateFlow
     * объект типа UIState<T> со значением по умолчанию UIState.Idle().
     */
    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    /**
     * [gatherRequest] - функция-расширение, которая принимает Flow<Either<String, T>>,
     * MutableStateFlow<UIState<S>> и функцию mappedData. Она запускает новый корутин в
     * IO-контексте и собирает результат запроса. Результат запроса представлен
     * объектом типа Either, который может быть либо Left (строка ошибки), либо Right
     * (успешный результат). В зависимости от значения объекта Either обновляется
     * значение объекта MutableStateFlow в соответствии с состоянием UI: Loading, Error,
     * или Success. Функция mappedData используется для преобразования объекта типа T в
     * объект типа S, который будет возвращаться в состоянии Success.
     */
    protected fun <T, S> Flow<Either<String, T>>.gatherRequest(
        state: MutableStateFlow<UIState<S>>,
        mappedData: (data: T) -> S
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = UIState.Success(mappedData(it.value))
                }
            }
        }
    }

    /**
     * [gatherRequest] - перегруженная версия функции gatherRequest(),
     * которая принимает только Flow<Either<String, T>> и MutableStateFlow<UIState<T>>,
     * и обновляет объект MutableStateFlow в соответствии с состоянием UI: Loading, Error, или Success.
     */
    protected fun <T> Flow<Either<String, T>>.gatherRequest(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value =
                        UIState.Success(it.value)
                }
            }
        }
    }
}