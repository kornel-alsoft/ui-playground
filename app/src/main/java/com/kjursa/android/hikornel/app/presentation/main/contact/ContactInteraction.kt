package com.kjursa.android.hikornel.app.presentation.main.contact

import com.kjursa.android.hikornel.arch.BaseInteraction

interface ContactInteraction : BaseInteraction {
    fun onClickedScreen(name: String)
    fun onNameChanged(name: String)
    fun onEmailChanged(email: String)
    fun onProjectChanged(project: String)
    fun onSendButtonClicked()
}