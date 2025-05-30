package com.kjursa.android.hikornel.app.presentation.main.info

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
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class InfoScreen @Inject constructor(
    factory: InfoViewModelFactory,
): BaseScreen<InfoViewState, InfoInteraction, InfoViewModel>(
    viewModelFactory = factory,
    viewModelClass = InfoViewModel::class
) {
    @Composable
    override fun Content(
        viewState: InfoViewState,
        interaction: InfoInteraction
    ) {
        InfoScreenContent(viewState, interaction)
    }
}

@Parcelize
data class InfoViewState(
    val name: String = "",
) : BaseViewState

interface InfoInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class InfoViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<InfoViewState>
) : BaseViewModel<InfoViewState, InfoInteraction>(
    viewStateProvider = viewStateProvider
), InfoInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class InfoViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(InfoViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = InfoViewState(name = "Profile")
            return InfoViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun InfoScreenContent(state: InfoViewState, interaction: InfoInteraction) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${state.name} screen"
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${state.name} screen"
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${state.name} screen"
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${state.name} screen"
        )
        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(onClick = { interaction.onClickedScreen("test") }) {
            Text(text = "Info")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}