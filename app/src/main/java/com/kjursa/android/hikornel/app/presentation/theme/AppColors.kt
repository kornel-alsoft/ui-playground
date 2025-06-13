package com.kjursa.android.hikornel.app.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors provided")
}

val LightAppColors = AppColors(
    cardBackgroundPrimary = Color(0xFFAEEBD6),
    cardBackgroundSecondary = Color(0xFFDFFFE8),
    cardBackgroundTertiary = Color(0xFFB2F1DF)
)

val DarkAppColors = AppColors(
    cardBackgroundPrimary = Color(0xFF2AA684),
    cardBackgroundSecondary = Color(0xFF33D7A0),
    cardBackgroundTertiary = Color(0xFFB2F1DF)
)

data class AppColors(
    val cardBackgroundPrimary: Color,
    val cardBackgroundSecondary: Color,
    val cardBackgroundTertiary: Color
)

object AppTheme {

    val colors: AppColors
        @ReadOnlyComposable
        @Composable
        get() = LocalAppColors.current

}

