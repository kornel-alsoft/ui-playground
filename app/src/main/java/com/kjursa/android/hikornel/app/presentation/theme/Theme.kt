package com.kjursa.android.hikornel.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


val LightColorScheme = lightColorScheme(
    primary = Mint,
    onPrimary = Color.White,

    secondary = Yellow,
    onSecondary = Color.Black,

    background = BackgroundLight,
    onBackground = OnBackgroundLight,

    surface = SurfaceLight,
    onSurface = OnSurfaceLight,

    error = ErrorRedLight,
    onError = Color.White
)

val DarkColorScheme = darkColorScheme(
    primary = Mint,
    onPrimary = Color.Black,

    secondary = Yellow,
    onSecondary = Color.Black,

    background = BackgroundDark,
    onBackground = OnBackgroundDark,

    surface = SurfaceDark,
    onSurface = OnSurfaceDark,

    error = ErrorRedDark,
    onError = Color.Black
)


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val appColors = if (darkTheme) DarkAppColors else LightAppColors

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = colors,
            typography = AppTypography,
            shapes = Shapes(),
            content = content
        )
    }
}