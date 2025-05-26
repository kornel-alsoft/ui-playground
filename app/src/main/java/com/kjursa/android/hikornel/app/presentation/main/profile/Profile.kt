package com.kjursa.android.hikornel.app.presentation.main.profile

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
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import com.kjursa.android.hikornel.ui.theme.Pink40
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class ProfileScreen @Inject constructor(
    factory: ProfileViewModelFactory,
): BaseScreen<ProfileViewState, ProfileInteraction, ProfileViewModel>(
    viewModelFactory = factory,
    viewModelClass = ProfileViewModel::class
) {
    @Composable
    override fun Content(
        viewState: ProfileViewState,
        interaction: ProfileInteraction
    ) {
        ProfileScreenContent(viewState, interaction)
    }
}

@Parcelize
data class ProfileViewState(
    val name: String = "",
) : BaseViewState

interface ProfileInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class ProfileViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<ProfileViewState>
) : BaseViewModel<ProfileViewState, ProfileInteraction>(
    viewStateProvider = viewStateProvider
), ProfileInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class ProfileViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = ProfileViewState(name = "Profile")
            return ProfileViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun ProfileScreenContent(state: ProfileViewState, interaction: ProfileInteraction) {
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
            Text(text = "Test")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}