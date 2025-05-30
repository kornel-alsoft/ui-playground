package com.kjursa.android.hikornel.app.presentation.main.contact

import com.kjursa.android.hikornel.arch.BaseViewState
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactViewState(
    val name: String = "",
    val email: String = "",
    val project: String = "",
    val step: ContactStep = ContactStep.ProvideData
) : BaseViewState {

    fun clear(): ContactViewState = copy(name = "", project = "", email = "")

    @IgnoredOnParcel
    val isSendButtonEnabled: Boolean =
        step == ContactStep.ProvideData &&
                name.isNotBlank() &&
                email.isNotBlank() &&
                project.isNotBlank()

    @IgnoredOnParcel
    val isLoading: Boolean = step == ContactStep.Sending

    @IgnoredOnParcel
    val isInputEnabled: Boolean = step == ContactStep.ProvideData
}

enum class ContactStep {
    ProvideData,
    Sending
}