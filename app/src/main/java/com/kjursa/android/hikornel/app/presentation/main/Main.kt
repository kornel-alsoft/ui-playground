package com.kjursa.android.hikornel.app.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
import com.kjursa.android.hikornel.ui.theme.CardBackground
import com.kjursa.android.hikornel.ui.theme.MainBackground
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Chat
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Email
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Home
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Settings
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Text

@Parcelize
data class MainViewState(
    val name: String = "",
    val prevRoute: String = "home",
) : BaseViewState

interface MainInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
    fun onEnterScreen(name: String)
    fun onSettingsClicked()
    fun onChatClicked()
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

    override fun onSettingsClicked() {
        navigationManager.navigateToSettings()
    }

    override fun onChatClicked() {
        navigationManager.navigateToChat()
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
                viewStateProvider = viewStateProvider(initial, savedState),
                navigationManager = navigationManager
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
    private val navigationManager: NavigationManager,
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
                    .padding(top = 48.dp)
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable(
                        "home",
                        enterTransition = {
                            if (state.prevRoute != "home") {
                                interaction.onEnterScreen("home")
                            }
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
//                        contactScreen.Screen()
                        profileScreen.Screen()
                    }
                }
            }

            Toolbar(
                avatarProgress,
                onSettingsClicked = interaction::onSettingsClicked,
                onChatClicked = interaction::onChatClicked
            )

            NavigationContent(
                modifier = Modifier.align(Alignment.BottomCenter),
                route = route,
                onChatClicked = { navigationManager.navigateToChat() }
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
fun Toolbar(
    progress: Float,
    onSettingsClicked: () -> Unit,
    onChatClicked: () -> Unit,
) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MainBackground)
        ) {
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(
                    imageVector = MyIconPack.Settings,
                    tint = lerp(Color.Black, Color.Black, progress),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickable { onSettingsClicked() }
                        .padding(horizontal = 2.dp)
                        .size(40.dp)
                        .padding(10.dp)
                )
            }
        }
//    }

    Icon(
        painter = painterResource(id = R.drawable.cropped_image),
        tint = Color.Unspecified,
        contentDescription = null,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .border(1.dp, Color.Black, CircleShape)
//            .offset(y = 12.dp.times(progress))
            .size(40.dp + 48.dp.times(progress))
//            .size(88.dp)
    )
}

@Composable
fun NavigationContent(
    modifier: Modifier,
    route: String,
    onChatClicked: () -> Unit,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .height(40.dp)
            .width(224.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.DarkGray) //, Color(0xFF555555))
                ),
                shape = RoundedCornerShape(24.dp)
            )
//            .padding(4.dp)
        ,
    ) {

        val highlightStart = remember { Animatable(0f) }
        val highlightEnd = remember { Animatable(1f) }
//        IconSelection(highlightStart.value, highlightEnd.value)
        var isFirstTime by remember { mutableStateOf(true) }

        if (route == "") return

        LaunchedEffect(route) {
            val (start, end) = when (route) {
                "home" -> 0f to 1f
//                "profile" -> 1f to 2f
                else -> 1f to 2f
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
            onClick = { currentRoute -> onClick(currentRoute) },
            onChatClicked = onChatClicked
        )
    }

    Box(modifier = modifier.padding(bottom = 4.dp)) {
        ChatIcon { onChatClicked() }
    }
}

@Composable
internal fun IconSelection(from: Float, to: Float) {
    val width = to - from
    Box(
        modifier = Modifier
            .width(216.dp.times(width / 2f))
            .offset(x = 216.dp.times(from / 2f))
            .fillMaxHeight()
            .background(Color.LightGray, RoundedCornerShape(24.dp))
    )
}

@Composable
internal fun NavigationIcons(route: String, onClick: (String) -> Unit, onChatClicked: () -> Unit) {
    val homeProgress by animateFloatAsState(
        targetValue = if (route == "home") 0f else 1f,
        animationSpec = tween()
    )
    val profileProgress by animateFloatAsState(
        targetValue = if (route == "profile") 0f else 1f,
        animationSpec = tween()
    )
    val contactProgress by animateFloatAsState(
        targetValue = if (route == "contact") 0f else 1f,
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
//        ProfileNavigationIcon(
//            tint = lerp(Color.White, Color.Black, profileProgress),
//            onClick = { onClick("profile") }
//        )
//        ChatIcon { onChatClicked() }
        Spacer(modifier = Modifier.weight(1f))
        ContactNavigationIcon(
            tint = lerp(Color.White, Color.Black, contactProgress),
            onClick = { onClick("contact") }
        )
    }
}

@Composable
internal fun ChatIcon(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(CircleShape)
            .background(Color.DarkGray)
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(MainBackground)
        ) {
            Icon(
                imageVector = MyIconPack.Chat,
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(44.dp)
                    .padding(12.dp)
            )
        }
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