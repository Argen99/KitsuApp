package com.example.kitsuapp.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.example.kitsuapp.core.ui_state.UIState
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * [BaseFragment] Абстрактный класс BaseFragment является базовым классом для всех фрагментов
 * в приложении и содержит общую логику, которую можно переопределить в наследниках.
 */
abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes) {

    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupRequest()
        setupObservers()
        setupListeners()
    }

    /**
     * [initialize] метод, который вызывается при создании фрагмента и может быть переопределен
     * в наследниках, чтобы инициализировать какие-либо необходимые данные или переменные.
     */
    protected open fun initialize() {}
    /**
     * [setupRequest] метод, который вызывается после инициализации и может быть переопределен в
     * наследниках, чтобы установить какие-либо запросы к серверу или базе данных.
     */
    protected open fun setupRequest() {}
    /**
     * [setupObservers] setupObservers() - метод, который вызывается после установки слушателей
     * и может быть переопределен в наследниках, чтобы установить наблюдателей за данными,
     * получаемыми из ViewModel или других источников.
     */
    protected open fun setupObservers() {}
    /**
     * [setupListeners] метод, который вызывается после установки запросов и может быть переопределен
     * в наследниках, чтобы установить слушатели для каких-либо View или других элементов
     * пользовательского интерфейса.
     */
    protected open fun setupListeners() {}

    /**
     * [safeFlowGather] для безопасной работы с жизненным циклом фрагмента
     */
    private fun safeFlowGather(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }

    /**
     * [spectatePaging] spectatePaging используется для слежения за изменениями данных
     * с помощью Paging Library. Она принимает объект Flow<PagingData<T>> и вызывает
     * обработчик успешного получения данных.
     */
    protected fun <T : Any> Flow<PagingData<T>>.spectatePaging(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: suspend (data: PagingData<T>) -> Unit,
    ) {
        safeFlowGather(lifecycleState) {
            collectLatest {
                success(it)
            }
        }
    }

    /**
     * [spectateUiState] spectateUiState используется для слежения за изменениями состояния
     * пользовательского интерфейса. Она принимает объект StateFlow<UIState<T>> и вызывает
     * обработчики событий в зависимости от текущего состояния.
     */
    protected fun <T> StateFlow<UIState<T>>.spectateUiState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((data: UIState.Loading<T>) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        idle: ((idle: UIState.Idle<T>) -> Unit)? = null,
        gatherIfSucceed: ((state: UIState<T>) -> Unit)? = null,
    ) {
        safeFlowGather(lifecycleState) {
            collect {
                gatherIfSucceed?.invoke(it)
                when (it) {
                    is UIState.Idle -> {
                        idle?.invoke(it)
                    }
                    is UIState.Loading -> {
                        loading?.invoke(it)
                    }
                    is UIState.Error -> {
                        error?.invoke(it.error)
                    }
                    is UIState.Success -> {
                        success?.invoke(it.data)
                    }
                }
            }
        }
    }
}