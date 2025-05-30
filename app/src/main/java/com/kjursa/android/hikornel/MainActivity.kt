package com.kjursa.android.hikornel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kjursa.android.hikornel.app.presentation.app.AppScreen
import com.kjursa.android.hikornel.ui.theme.HiKornelTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {


    @Inject
    lateinit var appScreen: AppScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HiKornelTheme {
                Scaffold { padding ->
                    Column(modifier = Modifier.padding(padding)) {

                        appScreen.Screen()

                    }
                }
            }
        }
    }





}

internal object AppRoute {
    const val MAIN = "main"
    const val SETTINGS = "settings"
    const val CHAT = "chat"

}

interface NavigationManager {
    fun navigateToChat()
    fun navigateToSettings()
}

interface NavigationInitializer {
    fun init(controller: NavHostController)
}

@Singleton
class AppNavigationManager @Inject constructor() : NavigationManager, NavigationInitializer {

    lateinit var controller: NavHostController

    override fun init(controller: NavHostController) {
        this.controller = controller
    }

    override fun navigateToChat() {
        controller.navigate(AppRoute.CHAT)
    }

    override fun navigateToSettings() {
        controller.navigate(AppRoute.SETTINGS)
    }
}