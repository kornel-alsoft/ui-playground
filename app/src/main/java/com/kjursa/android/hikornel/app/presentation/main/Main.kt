package com.kjursa.android.hikornel.app.presentation.main

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.kjursa.android.hikornel.app.presentation.app.settings.AppNavigation
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Email
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Home
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Text

@Parcelize
data class MainViewState(
    val name: String = "",
    val prevRoute: String = "home",
) : BaseViewState

interface MainInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
    fun onEnterScreen(name: String)
}


internal class MainViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<MainViewState>
) : BaseViewModel<MainViewState, MainInteraction>(
    viewStateProvider = viewStateProvider
), MainInteraction {

    override fun onClickedScreen(name: String) {

    }

    override fun onEnterScreen(name: String) {
        updateState { copy(prevRoute = name) }
    }
}

class MainViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = MainViewState(name = "Main")
            return MainViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

internal class MainScreen @Inject constructor(
    factory: MainViewModelFactory,
    private val homeScreen: HomeScreen,
    private val profileScreen: ProfileScreen,
    private val contactScreen: ContactScreen,
) : BaseScreen<MainViewState, MainInteraction, MainViewModel>(
    viewModelFactory = factory,
    viewModelClass = MainViewModel::class
) {
    @Composable
    override fun Content(
        viewState: MainViewState,
        interaction: MainInteraction
    ) {
        MainScreenContent(viewState, interaction)
    }

    @Composable
    fun MainScreenContent(state: MainViewState, interaction: MainInteraction) {

        val navController: NavHostController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val route = navBackStackEntry?.destination?.route ?: ""
//        var prevRoute by remember { mutableStateOf("home") }
//        Log.d("TEST_KORNEL", "route -> $route")
//        Log.d("TEST_KORNEL", "prev from VS -> ${state.prevRoute}")
        val avatarProgress by animateFloatAsState(
            targetValue = when (route) {
                "" -> if (state.prevRoute == "home") 1f else 0f
                "home" -> 1f
                else -> 0f
            },
            animationSpec = tween()
        )

        Box(modifier = Modifier.fillMaxSize()) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp)
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable(
                        "home",
                        enterTransition = {
                            if (state.prevRoute != "home") {
                                interaction.onEnterScreen("home")
                            }
                            interaction.onEnterScreen("home")
                            slideInLeft()

                        },
                        exitTransition = { slideOutLeft() }
                    ) {
                        homeScreen.Screen()
                    }
                    composable(
                        route = "profile",
                        enterTransition = {
                            if (state.prevRoute != "profile") {
                                interaction.onEnterScreen("profile")
                            }
                            when (initialState.destination.route) {
                                "home" -> slideInRight()
                                else -> slideInLeft()
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                "home" -> slideOutRight()
                                else -> slideOutLeft()
                            }
                        }
                    ) {
                        profileScreen.Screen()
                    }
                    composable(
                        route = "contact",
                        enterTransition = {
                            if (state.prevRoute != "contact") {
                                interaction.onEnterScreen("contact")
                            }
                            slideInRight()
                        },
                        exitTransition = { slideOutRight() }
                    ) {
                        contactScreen.Screen()
                    }
                }
            }

            Toolbar(avatarProgress) {
                AppNavigation.navigateToSettings()
            }

            NavigationContent(
                modifier = Modifier.align(Alignment.BottomCenter),
                route = route,
            ) { selectedRoute ->
                navController.navigate(selectedRoute) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }
        }
    }
}

private fun slideOutLeft(): ExitTransition =
    slideOut(
        targetOffset = { fullSize -> IntOffset(-fullSize.width / 3, 0) }
    ) + fadeOut()

private fun slideOutRight(): ExitTransition =
    slideOut(
        targetOffset = { fullSize -> IntOffset(fullSize.width / 3, 0) }
    ) + fadeOut()

private fun slideInLeft(): EnterTransition =
    slideIn(
        initialOffset = { fullSize -> IntOffset(-fullSize.width / 3, 0) }
    ) + fadeIn()

private fun slideInRight(): EnterTransition =
    slideIn(
        initialOffset = { fullSize -> IntOffset(fullSize.width / 3, 0) }
    ) + fadeIn()

@Composable
fun Toolbar(progress: Float, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.Black)
    ) {
        Icon(
            imageVector = MyIconPack.Text,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(24.dp))
                .clickable { onClick() }
                .padding(horizontal = 16.dp)
                .size(40.dp)
                .padding(10.dp)
        )
    }
    Icon(
        painter = painterResource(id = R.drawable.cropped_image),
        tint = Color.Unspecified,
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .offset(y = 72.dp.times(progress))
            .size(48.dp + 32.dp.times(progress))
    )

}

@Composable
fun NavigationContent(modifier: Modifier, route: String, onClick: (String) -> Unit) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .height(48.dp)
            .width(224.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color(0xFF555555))
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(4.dp),
    ) {

        val highlightStart = remember { Animatable(0f) }
        val highlightEnd = remember { Animatable(0.333f) }
        IconSelection(highlightStart.value, highlightEnd.value)
        var isFirstTime by remember { mutableStateOf(true) }

        if (route == "") return

        LaunchedEffect(route) {
            val (start, end) = when (route) {
                "home" -> 0f to 1 / 3f
                "profile" -> 1 / 3f to 2 / 3f
                else -> 2 / 3f to 1f
            }

            if (start > highlightStart.value) {
                if (isFirstTime) {
                    highlightEnd.snapTo(end)
                    highlightStart.snapTo(start)
                } else {
                    highlightEnd.animateTo(end)
                    highlightStart.animateTo(start)
                }
            } else {
                if (isFirstTime) {
                    highlightStart.snapTo(start)
                    highlightEnd.snapTo(end)
                } else {
                    highlightStart.animateTo(start)
                    highlightEnd.animateTo(end)
                }
            }

            isFirstTime = false
        }
        NavigationIcons(
            route,
            onClick = { currentRoute -> onClick(currentRoute) }
        )
    }
}

@Composable
internal fun IconSelection(from: Float, to: Float) {
    val width = to - from
    Box(
        modifier = Modifier
            .width(216.dp.times(width))
            .offset(x = 216.dp.times(from))
            .fillMaxHeight()
            .background(Color.LightGray, RoundedCornerShape(24.dp))
    )
}

@Composable
internal fun NavigationIcons(route: String, onClick: (String) -> Unit) {
    val homeProgress by animateFloatAsState(
        targetValue = if (route == "home") 1f else 0f,
        animationSpec = tween()
    )
    val profileProgress by animateFloatAsState(
        targetValue = if (route == "profile") 1f else 0f,
        animationSpec = tween()
    )
    val contactProgress by animateFloatAsState(
        targetValue = if (route == "contact") 1f else 0f,
        animationSpec = tween()
    )
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeNavigationIcon(
            tint = lerp(Color.White, Color.Black, homeProgress),
            onClick = { onClick("home") }
        )
        Spacer(modifier = Modifier.weight(1f))
        ProfileNavigationIcon(
            tint = lerp(Color.White, Color.Black, profileProgress),
            onClick = { onClick("profile") }
        )
        Spacer(modifier = Modifier.weight(1f))
        ContactNavigationIcon(
            tint = lerp(Color.White, Color.Black, contactProgress),
            onClick = { onClick("contact") }
        )
    }
}

@Composable
internal fun NavigationIcon(
    imageVector: ImageVector,
    tint: Color,
    onClick: () -> Unit
) {
    Icon(
        imageVector = imageVector,
        tint = tint,
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp)
            .size(40.dp)
            .padding(10.dp)
    )
}

@Composable
internal fun HomeNavigationIcon(
    tint: Color,
    onClick: () -> Unit
) {
    NavigationIcon(
        imageVector = MyIconPack.Home,
        tint = tint,
        onClick = onClick
    )
}

@Composable
internal fun ProfileNavigationIcon(
    tint: Color,
    onClick: () -> Unit
) {
    NavigationIcon(
        imageVector = MyIconPack.Text,
        tint = tint,
        onClick = onClick
    )
}

@Composable
internal fun ContactNavigationIcon(
    tint: Color,
    onClick: () -> Unit
) {
    NavigationIcon(
        imageVector = MyIconPack.Email,
        tint = tint,
        onClick = onClick
    )
}