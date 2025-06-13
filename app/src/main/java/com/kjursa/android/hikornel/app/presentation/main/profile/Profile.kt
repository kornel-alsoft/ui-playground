package com.kjursa.android.hikornel.app.presentation.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.kjursa.android.hikornel.NavigationManager
import com.kjursa.android.hikornel.app.presentation.theme.AppTheme
import com.kjursa.android.hikornel.arch.BaseInteraction
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.arch.BaseViewModel
import com.kjursa.android.hikornel.arch.BaseViewState
import com.kjursa.android.hikornel.arch.ViewStateProvider
import com.kjursa.android.hikornel.arch.viewStateProvider
import com.kjursa.android.hikornel.ui.theme.CardBackground
import com.kjursa.android.hikornel.ui.theme.MainBackground
import com.kjursa.android.hikornel.ui.theme.Pink40
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Chat
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Email
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Text
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

internal class ProfileScreen @Inject constructor(
    factory: ProfileViewModelFactory,
) : BaseScreen<ProfileViewState, ProfileInteraction, ProfileViewModel>(
    viewModelFactory = factory,
    viewModelClass = ProfileViewModel::class
) {
    @Composable
    override fun Content(
        viewState: ProfileViewState,
        interaction: ProfileInteraction
    ) {
        ProfileScreenContent(viewState, interaction)
    }
}

@Parcelize
data class ProfileViewState(
    val name: String = "",
) : BaseViewState

interface ProfileInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
}


internal class ProfileViewModel(
    private val navigationManager: NavigationManager,
    viewStateProvider: ViewStateProvider<ProfileViewState>
) : BaseViewModel<ProfileViewState, ProfileInteraction>(
    viewStateProvider = viewStateProvider
), ProfileInteraction {

    override fun onClickedScreen(name: String) {

    }
}

class ProfileViewModelFactory @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            val savedState = extras.createSavedStateHandle()
            val initial = ProfileViewState(name = "Profile")
            return ProfileViewModel(
                navigationManager,
                viewStateProvider = viewStateProvider(initial, savedState)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun ProfileScreenContent(state: ProfileViewState, interaction: ProfileInteraction) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.padding(top = 48.dp))
        Spacer(modifier = Modifier.padding(top = 16.dp))
        ContactIcons()
        Spacer(modifier = Modifier.padding(top = 16.dp))

        Spacer(modifier = Modifier.padding(top = 16.dp))

        HorizontalDivider(color = MaterialTheme.colorScheme.primary)
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "About me",
            modifier = Modifier.padding(vertical = 12.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.primary)
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "My experience",
            modifier = Modifier.padding(vertical = 12.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.primary)
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Contact me",
            modifier = Modifier.padding(vertical = 12.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.primary)
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Let's talk",
            modifier = Modifier.padding(vertical = 12.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.primary)
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Settings",
            modifier = Modifier.padding(vertical = 12.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.primary)

    }
}

@Composable
private fun ContactIcons() {

    ContactItem(
        value = "kornel.jursa@gmail.com",
        image = MyIconPack.Email
    )

    Spacer(modifier = Modifier.size(16.dp))

    ContactItem(
        value = "+48 600 823 720",
        image = MyIconPack.Chat
    )
    Spacer(modifier = Modifier.size(16.dp))

    ContactItem(
        value = "linkedin.com/n/kjursa",
        image = MyIconPack.Text
    )

}

@Composable
private fun ContactItem(
    value: String,
    image: ImageVector
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = image,
            tint = Color.Black,
            contentDescription = null,
            modifier = Modifier
                .background(AppTheme.colors.cardBackgroundPrimary, CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .size(40.dp)
                .padding(10.dp)
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }

}