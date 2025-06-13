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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.app.presentation.theme.AppTheme
import com.kjursa.android.hikornel.app.presentation.widget.Card
import com.kjursa.android.hikornel.app.presentation.widget.PrimarySkill
import com.kjursa.android.hikornel.app.presentation.widget.Skill
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import kotlinx.parcelize.Parcelize
import java.util.ArrayDeque
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
    fun onAboutMeCardClicked()
}


internal class HomeViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<HomeViewState>
) : BaseViewModel<HomeViewState, HomeInteraction>(
    viewStateProvider = viewStateProvider
), HomeInteraction {

    override fun onClickedScreen(name: String) {

    }

    override fun onAboutMeCardClicked() {
        navigationManager.navigateToChat()
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
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
//        Spacer(modifier = Modifier.size(16.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Box(modifier = Modifier.size(80.dp))
//            Text(text = "Hey,", style = MaterialTheme.typography.displayLarge)
//        }
        Text(
            text = "Hello,",
            modifier = Modifier.padding(start = 0.dp),
            style = MaterialTheme.typography.displayLarge
        )
        Text(text = "I'm Kornel", style = MaterialTheme.typography.displayLarge)

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PrimarySkill(name = "Senior Android Developer")
        }
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Skill(name = "Kotlin")
            Skill(name = "Jetpack Compose")
        }
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Skill(name = "Coroutines")
            Skill(name = "Clean architecture")
        }


//        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.size(16.dp))
        Card(
            title = "About me",
            body = "I'm Kornel, Android Developer specializing in Kotlin, Jetpack Compose, Coroutines and clean architecture. Let's talk with my virtual AI assistant to know more about me",
            color = AppTheme.colors.cardBackgroundPrimary,
            onClick = interaction::onAboutMeCardClicked
        )

        Spacer(modifier = Modifier.size(16.dp))
        Card(
            title = "My Experience",
            body = "Android developer with over 10 years in Android development. I have been working with interanational teams",
            color = AppTheme.colors.cardBackgroundPrimary,
            onClick = interaction::onAboutMeCardClicked
        )

        Spacer(modifier = Modifier.size(16.dp))
        Card(
            title = "Let's talk",
            body = "My virtual AI assistant can answer your questions. Let's talk. ",
            color = AppTheme.colors.cardBackgroundPrimary,
            onClick = interaction::onAboutMeCardClicked
        )
//        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.size(64.dp))
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Green)
    )
}