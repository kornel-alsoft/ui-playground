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
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var messageProvider: WelcomeMessageProvider

    @Inject
    lateinit var navigationManager: AppNavigationManager

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HiKornelTheme {
                Scaffold { padding ->
                    Column(modifier = Modifier.padding(padding)) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = messageProvider.message()
                        )
                        MyAppNavHost(navigationManager)
                    }
                }
            }
        }
    }


    @Composable
    fun MyAppNavHost(navigation: AppNavigationManager) {
        val navController: NavHostController = rememberNavController()
        navigation.controller = navController

        NavHost(navController = navController, startDestination = NavScreen.Login.route) {
            composable(NavScreen.Login.route) {
                LoginScreen()
            }
            composable(
                route = NavScreen.Home.route,
                arguments = listOf(userNameNavArgument)
            ) {
                HomeScreen()
            }
            composable(
                route = NavScreen.Settings.route,
                arguments = listOf(userNameNavArgument)
            ) {
                SettingsScreen(navigation)
            }
        }
    }

    private val userNameNavArgument = navArgument("userName") {
        type = NavType.StringType
    }

    @Composable
    fun LoginScreen() {
        ComposableScreenFactory<LoginViewModel, LoginViewState, LoginInteraction>(
            factory = { loginViewModelFactory }
        ) { state, inter ->
            LoginScreenContent(state, inter)
        }
    }

    @Composable
    fun HomeScreen() {
        val homeFactory = remember { homeViewModelFactory }

        ComposableScreenFactory<HomeViewModel, HomeViewState, HomeInteraction>(
            factory = { homeFactory }
        ) { state, inter ->
            HomeScreenContent(state, inter)
        }

    }

    @Composable
    fun SettingsScreen(navigation: NavigationManager) {
        val settingsFactory = remember { SettingsViewModelFactory(navigationManager = navigation) }

        ComposableScreenFactory<SettingsViewModel, SettingsViewState, SettingsInteraction>(
            factory = { settingsFactory }
        ) { state, inter ->
            SettingsScreenContent(state, inter)
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