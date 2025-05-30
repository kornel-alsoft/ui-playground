package com.kjursa.android.hikornel.app.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.app.presentation.main.home.Divider
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Moon
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Sun
import com.kjursa.android.hikornel.app.presentation.widget.AnimatedIconSwitch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class ChatScreen @Inject constructor(
    factory: ChatViewModelFactory,
): BaseScreen<ChatViewState, ChatInteraction, ChatViewModel>(
    viewModelFactory = factory,
    viewModelClass = ChatViewModel::class
) {
    @Composable
    override fun Content(
        viewState: ChatViewState,
        interaction: ChatInteraction
    ) {
        ChatScreenContent(viewState, interaction)
    }
}

@Parcelize
data class ChatViewState(
    val name: String = "",
) : BaseViewState

interface ChatInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class ChatViewModel(
    viewStateProvider: ViewStateProvider<ChatViewState>
) : BaseViewModel<ChatViewState, ChatInteraction>(
    viewStateProvider = viewStateProvider
), ChatInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class ChatViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = ChatViewState(name = "Chat")
            return ChatViewModel(
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun ChatScreenContent(state: ChatViewState, interaction: ChatInteraction) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Kornel Jursa"
        )
        Divider()
        Text(
            text = "kornel.jursa@gmail.com"
        )
        Divider()
        Text(
            text = "www.kjursa.com"
        )
        Spacer(modifier = Modifier.weight(1f))

        var isLightModeOn by remember { mutableStateOf(false) }
        AnimatedIconSwitch(
            checked = isLightModeOn,
            onCheckedChange = { isLightModeOn = it },
            imageOn = MyIconPack.Sun,
            imageOff = MyIconPack.Moon,
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}