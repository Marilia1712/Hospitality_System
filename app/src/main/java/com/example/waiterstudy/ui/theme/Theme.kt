package com.example.waiterstudy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColors = darkColorScheme(
    primary = DarkButton,
    secondary = BlueButton,
    background = BackgroundGray,
    surface = DarkButton,
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onBackground = WhiteText,
    onSurface = WhiteText
)

@Composable
fun WaiterStudyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = Typography,
        content = content
    )
}