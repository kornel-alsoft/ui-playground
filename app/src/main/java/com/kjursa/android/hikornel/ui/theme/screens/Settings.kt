package com.kjursa.android.hikornel.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsViewState(
    val userName: String,
    val isNotificationOn: Boolean = false,
    val isUseFaceIdOn: Boolean = false,
) : BaseViewState

interface SettingsInteraction : BaseInteraction {
    fun onNotificationClicked(isChecked: Boolean)
    fun onFaceIdClicked(isChecked: Boolean)
    fun onCloseClick()
}

internal class SettingsViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<SettingsViewState>,
) : BaseViewModel<SettingsViewState, SettingsInteraction>(
    viewStateProvider
), SettingsInteraction {

    override fun onNotificationClicked(isChecked: Boolean) {
        updateState { copy(isNotificationOn = isChecked) }
    }

    override fun onFaceIdClicked(isChecked: Boolean) {
        updateState { copy(isUseFaceIdOn = isChecked) }
    }

    override fun onCloseClick() {
//        navigationManager.navigateBack()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TEST_KORNEL", "onCleared SettingsViewModel")
    }
}

class SettingsViewModelFactory(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        Log.d("TEST_KORNEL", "Creating SettingsViewModel")
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            val savedStateHandle = extras.createSavedStateHandle()
            val userName = savedStateHandle["userName"] ?: "n/a"
            val viewStateProvider = viewStateProvider(
                initialState = SettingsViewState(userName),
                savedStateHandle = savedStateHandle
            )
            return SettingsViewModel(navigationManager, viewStateProvider) as T
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
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "${state.userName}'s Settings"
        )
        Spacer(modifier = Modifier.size(32.dp))
        Text(text = "Notification")
        SwitchRow(
            title = "Notification",
            checked = state.isNotificationOn,
            onCheckedChange = interaction::onNotificationClicked
        )
        SwitchRow(
            title = "FaceID",
            checked = state.isUseFaceIdOn,
            onCheckedChange = interaction::onFaceIdClicked
        )
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
private fun SwitchRow(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val text = if (checked) "On" else "Off"
        Text(text = "$title: $text")
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

