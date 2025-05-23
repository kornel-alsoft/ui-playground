package com.kjursa.android.hikornel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kjursa.android.hikornel.app.presentation.main.MainScreen
import com.kjursa.android.hikornel.arch.ComposableScreenFactory
import com.kjursa.android.hikornel.di.WelcomeMessageProvider
import com.kjursa.android.hikornel.ui.theme.HiKornelTheme
import com.kjursa.android.hikornel.ui.theme.screens.LoginInteraction
import com.kjursa.android.hikornel.ui.theme.screens.LoginScreenContent
import com.kjursa.android.hikornel.ui.theme.screens.LoginViewModel
import com.kjursa.android.hikornel.ui.theme.screens.LoginViewModelFactory
import com.kjursa.android.hikornel.ui.theme.screens.LoginViewState
import com.kjursa.android.hikornel.ui.theme.screens.HomeInteraction
import com.kjursa.android.hikornel.ui.theme.screens.HomeScreenContent
import com.kjursa.android.hikornel.ui.theme.screens.HomeViewModel
import com.kjursa.android.hikornel.ui.theme.screens.HomeViewModelFactory
import com.kjursa.android.hikornel.ui.theme.screens.HomeViewState
import com.kjursa.android.hikornel.ui.theme.screens.SettingsInteraction
import com.kjursa.android.hikornel.ui.theme.screens.SettingsScreenContent
import com.kjursa.android.hikornel.ui.theme.screens.SettingsViewModel
import com.kjursa.android.hikornel.ui.theme.screens.SettingsViewModelFactory
import com.kjursa.android.hikornel.ui.theme.screens.SettingsViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {


    @Inject
    lateinit var mainScreen: MainScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HiKornelTheme {
                Scaffold { padding ->
                    Column(modifier = Modifier.padding(padding)) {

                        mainScreen.Screen()

                    }
                }
            }
        }
    }





}

internal sealed class NavScreen(val route: String) {
    open fun build(param: String): String = route

    data object Login : NavScreen("login")

    data object Home : NavScreen("home/{userName}") {
        override fun build(param: String): String = "home/$param"
    }

    data object Settings : NavScreen("settings/{userName}") {
        override fun build(param: String): String = "settings/$param"
    }
}

interface NavigationManager {
    fun navigateToHome(userName: String)
    fun navigateToLogin()
    fun navigateBack()
    fun navigateToSettings(userName: String)
}

@Singleton
class AppNavigationManager @Inject constructor() : NavigationManager {

    lateinit var controller: NavHostController

    override fun navigateToHome(userName: String) {
        controller.navigate(NavScreen.Home.build(userName))
    }

    override fun navigateToLogin() {
        controller.navigate(NavScreen.Login.route)
    }

    override fun navigateBack() {
        controller.navigateUp()
    }

    override fun navigateToSettings(userName: String) {
        controller.navigate(NavScreen.Settings.build(userName))
    }
}