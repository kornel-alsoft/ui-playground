package com.kjursa.android.hikornel.app.presentation.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import com.kjursa.android.hikornel.ui.theme.PurpleGrey40
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class HomeScreen @Inject constructor(
    factory: HomeViewModelFactory,
) : BaseScreen<HomeViewState, HomeInteraction, HomeViewModel>(
    viewModelFactory = factory,
    viewModelClass = HomeViewModel::class
) {
    @Composable
    override fun Content(
        viewState: HomeViewState,
        interaction: HomeInteraction
    ) {
        HomeScreenContent(viewState, interaction)
    }
}

@Parcelize
data class HomeViewState(
    val name: String = "",
) : BaseViewState

interface HomeInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class HomeViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<HomeViewState>
) : BaseViewModel<HomeViewState, HomeInteraction>(
    viewStateProvider = viewStateProvider
), HomeInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class HomeViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = HomeViewState(name = "Home")
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
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey40)
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(modifier = Modifier.size(80.dp))
            Text(text = "Hey,", style = MaterialTheme.typography.displayLarge)
        }

        Text(text = "I'm Kornel", style = MaterialTheme.typography.displayLarge)

        Spacer(modifier = Modifier.weight(1f))
        Divider()
        Spacer(modifier = Modifier.size(64.dp))
    }
}

@Composable
fun Divider() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color.Green))
}