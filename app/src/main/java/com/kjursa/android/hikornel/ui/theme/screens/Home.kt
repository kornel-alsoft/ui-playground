package com.kjursa.android.hikornel.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import javax.inject.Inject

@Parcelize
data class HomeViewState(
    val username: String,
    val hello: String = "",
) : BaseViewState

interface HomeInteraction : BaseInteraction {
    fun onSettingsClicked()
    fun onCloseClick()
    fun name(): String
}


internal class HomeViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<HomeViewState>
) : BaseViewModel<HomeViewState, HomeInteraction>(
    viewStateProvider = viewStateProvider
), HomeInteraction {

    override fun onSettingsClicked() {
//        navigationManager.navigateToSettings(viewState.username)
    }

    override fun onCloseClick() {
//        navigationManager.navigateBack()
    }

    override fun name(): String {
        return "HomeViewModel"
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TEST_KORNEL", "onCleared HomeViewModel")
    }
}

class HomeViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val userName = savedState.get<String>("userName")
            val initial = HomeViewState(username = userName ?: "")
            return HomeViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun HomeScreenContent(state: HomeViewState, interaction: HomeInteraction) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Home Screen"
        )
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Hello, ${state.username}"
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "How you feel today?"
        )
        Spacer(modifier = Modifier.size(32.dp))
        Button(onClick = interaction::onSettingsClicked) {
            Text(text = "Settings")
        }
        Spacer(modifier = Modifier.size(32.dp))
        OutlinedButton(onClick = interaction::onCloseClick) {
            Text(text = "Close")
        }
    }
}