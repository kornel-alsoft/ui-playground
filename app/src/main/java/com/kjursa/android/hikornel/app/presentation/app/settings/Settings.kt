package com.kjursa.android.hikornel.app.presentation.app.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import com.kjursa.android.hikornel.ui.theme.Purple40
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Moon
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Sun
import com.kjursa.android.hikornel.ui.theme.widget.AnimatedIconSwitch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class SettingsScreen @Inject constructor(
    factory: SettingsViewModelFactory,
): BaseScreen<SettingsViewState, SettingsInteraction, SettingsViewModel>(
    viewModelFactory = factory,
    viewModelClass = SettingsViewModel::class
) {
    @Composable
    override fun Content(
        viewState: SettingsViewState,
        interaction: SettingsInteraction
    ) {
        SettingsScreenContent(viewState, interaction)
    }
}

@Parcelize
data class SettingsViewState(
    val name: String = "",
) : BaseViewState

interface SettingsInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class SettingsViewModel(
    viewStateProvider: ViewStateProvider<SettingsViewState>
) : BaseViewModel<SettingsViewState, SettingsInteraction>(
    viewStateProvider = viewStateProvider
), SettingsInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class SettingsViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = SettingsViewState(name = "Settings")
            return SettingsViewModel(
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun SettingsScreenContent(state: SettingsViewState, interaction: SettingsInteraction) {
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
        Box(Modifier.fillMaxWidth().height(80.dp).padding(16.dp).background(Purple40))
        Spacer(modifier = Modifier.weight(1f))

        var isLightModeOn by remember { mutableStateOf(false) }
        AnimatedIconSwitch(
            checked = isLightModeOn,
            onCheckedChange = { isLightModeOn = it },
            imageOn = MyIconPack.Sun,
            imageOff = MyIconPack.Moon,
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}