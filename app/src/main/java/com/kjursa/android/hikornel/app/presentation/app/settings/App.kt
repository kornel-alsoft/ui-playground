package com.kjursa.android.hikornel.app.presentation.app.settings

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.app.presentation.main.contact.ContactScreen
import com.kjursa.android.hikornel.app.presentation.main.home.HomeScreen
import com.kjursa.android.hikornel.app.presentation.main.profile.ProfileScreen
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import kotlinx.parcelize.Parcelize
import javax.inject.Inject
import com.kjursa.android.hikornel.R
import com.kjursa.android.hikornel.app.presentation.app.settings.SettingsScreen
import com.kjursa.android.hikornel.app.presentation.main.MainScreen
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Email
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Home
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Text

@Parcelize
data class AppViewState(
    val name: String = "",
) : BaseViewState

interface AppInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class AppViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<AppViewState>
) : BaseViewModel<AppViewState, AppInteraction>(
    viewStateProvider = viewStateProvider
), AppInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class AppViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = AppViewState(name = "Main")
            return AppViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

internal class AppScreen @Inject constructor(
    factory: AppViewModelFactory,
    private val mainScreen: MainScreen,
    private val settingsScreen: SettingsScreen,
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

        AppNavigation.controller = navController

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = "main") {
                composable(
                    "main",
                    enterTransition = {
                        slideInVertically(
                            initialOffsetY = { fullSize -> -fullSize / 3 }
                        ) + fadeIn()
                    },
                    exitTransition = {
                        slideOutVertically(
                            targetOffsetY = { fullSize ->  -fullSize / 3 }
                        ) + fadeOut()
                    }
                ) {
                    mainScreen.Screen()
                }
                composable(
                    "settings",
                    enterTransition = {
                        slideInVertically(
                            initialOffsetY = { fullSize -> fullSize / 3 }
                        ) + fadeIn()
                                      },
                    exitTransition = {
                        slideOutVertically(
                            targetOffsetY = { fullSize ->  fullSize / 3 }
                        ) + fadeOut()
                    }
                ) {
                    settingsScreen.Screen()
                }

            }
        }
    }
}

object AppNavigation {
    // TODO FIX ME this is a memory leak
    lateinit var controller: NavHostController

    fun navigateToSettings() {
        controller.navigate("settings")
    }

    fun navigateToChat() {
        controller.navigate("chat")
    }


}
