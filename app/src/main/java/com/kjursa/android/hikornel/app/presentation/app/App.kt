package com.kjursa.android.hikornel.app.presentation.app

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kjursa.android.hikornel.AppRoute
import com.kjursa.android.hikornel.NavigationInitializer
import com.kjursa.android.hikornel.app.presentation.chat.ChatScreen
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import com.kjursa.android.hikornel.app.presentation.main.MainScreen
import com.kjursa.android.hikornel.app.presentation.settings.SettingsScreen

@Parcelize
data class AppViewState(
    val name: String = "",
) : BaseViewState

interface AppInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class AppViewModel(
    viewStateProvider: ViewStateProvider<AppViewState>
) : BaseViewModel<AppViewState, AppInteraction>(
    viewStateProvider = viewStateProvider
), AppInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class AppViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = AppViewState(name = "Main")
            return AppViewModel(
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

internal class AppScreen @Inject constructor(
    factory: AppViewModelFactory,
    private val mainScreen: MainScreen,
    private val chatScreen: ChatScreen,
    private val settingsScreen: SettingsScreen,
    private val navigationInitializer: NavigationInitializer
) : BaseScreen<AppViewState, AppInteraction, AppViewModel>(
    viewModelFactory = factory,
    viewModelClass = AppViewModel::class
) {
    @Composable
    override fun Content(
        viewState: AppViewState,
        interaction: AppInteraction
    ) {
        AppScreenContent(viewState, interaction)
    }

    @Composable
    fun AppScreenContent(state: AppViewState, interaction: AppInteraction) {

        val navController: NavHostController = rememberNavController()
        navigationInitializer.init(navController)

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = AppRoute.MAIN) {
                composable(
                    route = AppRoute.MAIN,
                    enterTransition = { slideFromTop() },
                    exitTransition = { slideToTop() }
                ) {
                    mainScreen.Screen()
                }
                composable(
                    route = AppRoute.SETTINGS,
                    enterTransition = { slideFromBottom() },
                    exitTransition = { slideToBottom() }
                ) {
                    settingsScreen.Screen()
                }
                composable(
                    route = AppRoute.CHAT,
                    enterTransition = { slideFromBottom() },
                    exitTransition = { slideToBottom() }
                ) {
                    chatScreen.Screen()
                }

            }
        }
    }
}

private fun slideFromTop(): EnterTransition = slideInVertically(
    initialOffsetY = { fullSize -> -fullSize / 3 }
) + fadeIn()

private fun slideToTop(): ExitTransition = slideOutVertically(
    targetOffsetY = { fullSize ->  -fullSize / 3 }
) + fadeOut()

private fun slideFromBottom(): EnterTransition = slideInVertically(
    initialOffsetY = { fullSize -> fullSize / 3 }
) + fadeIn()

private fun slideToBottom(): ExitTransition = slideOutVertically(
    targetOffsetY = { fullSize ->  fullSize / 3 }
) + fadeOut()
