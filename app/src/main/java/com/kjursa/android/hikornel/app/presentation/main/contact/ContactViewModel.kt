package com.kjursa.android.hikornel.app.presentation.main.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ContactViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<ContactViewState>
) : BaseViewModel<ContactViewState, ContactInteraction>(
    viewStateProvider = viewStateProvider
), ContactInteraction {

    override fun onClickedScreen(name: String) {

    }

    override fun onNameChanged(name: String) {
        updateState { copy(name = name) }
    }

    override fun onEmailChanged(email: String) {
        updateState { copy(email = email) }
    }

    override fun onProjectChanged(project: String) {
        updateState { copy(project = project) }
    }

    override fun onSendButtonClicked() {
        updateState {
            copy(step = ContactStep.Sending)
        }
        viewModelScope.launch {
            delay(2000)
            updateState {
                clear().copy(step = ContactStep.ProvideData)
            }
        }
    }
}

class ContactViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = ContactViewState()
            return ContactViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

