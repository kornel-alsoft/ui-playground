package com.kjursa.android.hikornel.app.presentation.main.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kjursa.android.hikornel.R
import com.kjursa.android.hikornel.app.presentation.widget.button.PrimaryButton
import com.kjursa.android.hikornel.arch.BaseScreen
import com.kjursa.android.hikornel.ui.theme.CardBackground
import javax.inject.Inject

internal class ContactScreen @Inject constructor(
    factory: ContactViewModelFactory,
) : BaseScreen<ContactViewState, ContactInteraction, ContactViewModel>(
    viewModelFactory = factory,
    viewModelClass = ContactViewModel::class
) {
    @Composable
    override fun Content(
        viewState: ContactViewState,
        interaction: ContactInteraction
    ) {
        ContactScreenContent(viewState, interaction)
    }
}

@Composable
fun ContactScreenContent(state: ContactViewState, interaction: ContactInteraction) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(16.dp),
    ) {
        Title()
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = CardBackground
        )
        NameTextField(
            value = state.name,
            enabled = state.isInputEnabled,
            onNameChanged = interaction::onNameChanged
        )

        Spacer(modifier = Modifier.size(16.dp))

        EmailTextField(
            value = state.email,
            enabled = state.isInputEnabled,
            onEmailChanged = interaction::onEmailChanged
        )

        Spacer(modifier = Modifier.size(16.dp))

        ProjectTextField(
            value = state.project,
            enabled = state.isInputEnabled,
            onProjectChanged = interaction::onProjectChanged
        )

        Spacer(modifier = Modifier.size(16.dp))

        PrimaryButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .widthIn(min = 180.dp),
            text = stringResource(id = R.string.contact_send),
            enabled = state.isSendButtonEnabled,
            isLoading = state.isLoading,
            onClick = interaction::onSendButtonClicked
        )
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.contact_title).uppercase(),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun EmailTextField(
    value: String,
    enabled: Boolean,
    onEmailChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        label = stringResource(id = R.string.contact_email_title),
        onValueChange = onEmailChanged,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
    )
}

@Composable
private fun ProjectTextField(
    value: String,
    enabled: Boolean,
    onProjectChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        label = stringResource(id = R.string.contact_project_title),
        onValueChange = onProjectChanged,
        enabled = enabled,
        lines = 5,
    )
}

@Composable
private fun NameTextField(
    value: String,
    enabled: Boolean,
    onNameChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        label = stringResource(id = R.string.contact_name_title),
        enabled = enabled,
        onValueChange = onNameChanged
    )
}

@Composable
private fun TextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    lines: Int = 1,
    enabled: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        enabled = enabled,
        singleLine = lines == 1,
        minLines = lines,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}

