package com.kjursa.android.hikornel.app.presentation.main.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.app.presentation.main.home.HomeInteraction
import com.kjursa.android.hikornel.app.presentation.main.home.HomeScreenContent
import com.kjursa.android.hikornel.app.presentation.main.home.HomeViewState
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import com.kjursa.android.hikornel.ui.theme.Purple40
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class ContactScreen @Inject constructor(
    factory: ContactViewModelFactory,
): BaseScreen<ContactViewState, ContactInteraction, ContactViewModel>(
    viewModelFactory = factory,
    viewModelClass = ContactViewModel::class
) {
    @Composable
    override fun Content(
        viewState: ContactViewState,
        interaction: ContactInteraction
    ) {
        ContactScreenContent(viewState, interaction)
    }
}

@Parcelize
data class ContactViewState(
    val name: String = "",
) : BaseViewState

interface ContactInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class ContactViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<ContactViewState>
) : BaseViewModel<ContactViewState, ContactInteraction>(
    viewStateProvider = viewStateProvider
), ContactInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class ContactViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = ContactViewState(name = "Contact")
            return ContactViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun ContactScreenContent(state: ContactViewState, interaction: ContactInteraction) {
    Column(
        modifier = Modifier.fillMaxSize().background(Purple40),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${state.name} screen"
        )
        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(onClick = { interaction.onClickedScreen("test") }) {
            Text(text = "Test")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}