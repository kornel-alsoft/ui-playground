package com.kjursa.android.hikornel.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.provider
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Checkmark
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Cros
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Moon
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Sun
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Volumeoff
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Volumeon
import com.kjursa.android.hikornel.ui.theme.widget.AnimatedIconSwitch
import com.kjursa.android.hikornel.ui.theme.widget.rememberAnimatedIconSwitchStyle
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import kotlin.Int

interface LoginInteraction : BaseInteraction {
    fun onLoginClicked()
    fun onUsernameChanged(name: String)
    fun onPasswordChanged(password: String)
}

@Parcelize
data class LoginViewState(
    val id: Int = 0,
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
) : BaseViewState

internal class LoginViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<LoginViewState>
) : BaseViewModel<LoginViewState, LoginInteraction>(
    viewStateProvider = viewStateProvider
), LoginInteraction {

    override fun onLoginClicked() {
        navigationManager.navigateToHome(viewState.username)
    }

    override fun onUsernameChanged(name: String) {
        updateState { copy(username = name) }
    }

    override fun onPasswordChanged(password: String) {
        updateState { copy(password = password) }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TEST_KORNEL", "onCleared LoginViewModel")
    }
}

class LoginViewModelFactory @Inject constructor(
    val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                navigationManager,
                extras.provider(LoginViewState())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun LoginScreenContent(state: LoginViewState, interaction: LoginInteraction) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "LoginScreen (id: ${state.id})"
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = state.username,
            onValueChange = interaction::onUsernameChanged
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = interaction::onPasswordChanged
        )
        Spacer(modifier = Modifier.weight(1f))

        var isLightModeOn by remember { mutableStateOf(false) }
        AnimatedIconSwitch(
            checked = isLightModeOn,
            onCheckedChange = { isLightModeOn = it },
            imageOn = MyIconPack.Sun,
            imageOff = MyIconPack.Moon,
        )
        Spacer(modifier = Modifier.weight(1f))

        var isVolumeOn by remember { mutableStateOf(true) }
        AnimatedIconSwitch(
            checked = isVolumeOn,
            onCheckedChange = { isVolumeOn = it },
            imageOn = MyIconPack.Volumeon,
            imageOff = MyIconPack.Volumeoff,
        )

        Spacer(modifier = Modifier.weight(1f))

        var isTaskOn by remember { mutableStateOf(true) }
        AnimatedIconSwitch(
            checked = isTaskOn,
            onCheckedChange = { isTaskOn = it },
            imageOn = MyIconPack.Checkmark,
            imageOff = MyIconPack.Cros,
            style = rememberAnimatedIconSwitchStyle(
                backgroundOnColor = Color.Green,
                backgroundOffColor = Color.Red,
                iconOnTint = Color.White,
                iconOffTint = Color.White,
            )
        )

        Spacer(modifier = Modifier.weight(1f))


        Button(onClick = interaction::onLoginClicked) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.size(32.dp))
    }
}