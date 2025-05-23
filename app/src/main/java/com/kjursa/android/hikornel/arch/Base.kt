@file:Suppress("UNCHECKED_CAST")

package com.kjursa.android.hikornel.arch

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.reflect.KClass

interface BaseViewState : Parcelable

interface BaseInteraction

abstract class BaseViewModel<S : BaseViewState, I : BaseInteraction>(
    val viewStateProvider: ViewStateProvider<S>
) : ViewModel(), BaseInteraction {

    private val _viewState = MutableStateFlow(viewStateProvider.get())
    val viewStateFlow: StateFlow<S> = _viewState.asStateFlow()

    val viewState: S
        get() = viewStateFlow.value

    protected fun updateState(reducer: S.() -> S) {
        val newValue = viewState.reducer()
        viewStateProvider.set(newValue)
        _viewState.update { viewStateProvider.get() }
    }
}

@Composable
inline fun <reified VM, S : BaseViewState, I : BaseInteraction> ComposableScreen(
    noinline viewModelProvider: @Composable () -> VM ,
    crossinline content: @Composable (state: S, interaction: I) -> Unit
) where VM : BaseViewModel<S, I> {
    val vm = viewModelProvider()
    val state by vm.viewStateFlow.collectAsState()
    val interaction = vm as I
    content(state, interaction)
}

@Composable
inline fun <reified VM, S : BaseViewState, I : BaseInteraction> ComposableScreenFactory(
    noinline factory: (() -> ViewModelProvider.Factory)? = null,
    crossinline content: @Composable (state: S, interaction: I) -> Unit
) where VM : BaseViewModel<S, I> {
    val vm: VM = if (factory != null) {
        viewModel(factory = factory())
    } else {
        viewModel()
    }

    val state by vm.viewStateFlow.collectAsState()
    val interaction = vm as I
    content(state, interaction)
}

abstract class BaseScreen<
        S : BaseViewState,
        I : BaseInteraction,
        VM : BaseViewModel<S, I>
        >(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val viewModelClass: KClass<VM>
) {
    @Composable
    fun Screen() {
        val owner = LocalViewModelStoreOwner.current
            ?: error("No ViewModelStoreOwner was provided")
        val viewModel: VM = remember(viewModelFactory) {
            ViewModelProvider(owner, viewModelFactory)[viewModelClass.java]
        }

        val viewState by viewModel.viewStateFlow.collectAsStateWithLifecycle()

        Content(viewState = viewState, interaction = viewModel as I)
    }

    @Composable
    protected abstract fun Content(viewState: S, interaction: I)
}


interface ViewStateProvider<T : BaseViewState> {
    fun get(): T
    fun set(viewState: T)
}

fun <T : BaseViewState> viewStateProvider(initialState: T): ViewStateProvider<T> =
    InMemoryViewStateProvider(initialState)

internal class InMemoryViewStateProvider<T : BaseViewState>(
    initialValue: T
) : ViewStateProvider<T> {
    var value: T = initialValue

    override fun get(): T = value

    override fun set(viewState: T) {
        value = viewState
    }
}

fun <T : BaseViewState> SavedStateHandle.viewStateProvider(initialState: BaseViewState): ViewStateProvider<T> {
    return SaveStateHandleViewStateProvider(initialState, this) as ViewStateProvider<T>
}

fun <T : BaseViewState> viewStateProvider(
    initialState: T,
    savedStateHandle: SavedStateHandle
): ViewStateProvider<T> =
    SaveStateHandleViewStateProvider(initialState, savedStateHandle)

internal class SaveStateHandleViewStateProvider<T : BaseViewState>(
    initialValue: T,
    private val savedStateHandle: SavedStateHandle,
) : ViewStateProvider<T> {
    var value: T = savedStateHandle["viewState"] ?: initialValue

    override fun get(): T = value

    override fun set(viewState: T) {
        value = viewState
        savedStateHandle["viewState"] = viewState
    }
}

fun <VS: BaseViewState> CreationExtras.provider(initialViewState: VS): ViewStateProvider<VS> {
    return viewStateProvider(initialViewState, createSavedStateHandle())
}