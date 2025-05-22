package com.kjursa.android.hikornel.di

import com.kjursa.android.hikornel.AppNavigationManager
import com.kjursa.android.hikornel.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {

    @Binds
    fun provideRepository(impl: AndroidWelcomeMessageProvider): WelcomeMessageProvider

    @Binds
    @Singleton
    fun provideNavigationManager(impl: AppNavigationManager): NavigationManager
}

interface WelcomeMessageProvider {
    fun message(): String
}

internal class AndroidWelcomeMessageProvider @Inject constructor() : WelcomeMessageProvider {
    override fun message(): String = "Hello from Android!"
}
