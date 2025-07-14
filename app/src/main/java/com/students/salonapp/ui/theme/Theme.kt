package com.students.salonapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = salonPinkPrimary,
    onPrimary = salonPinkOnPrimary,
    primaryContainer = salonPinkPrimaryContainer,
    onPrimaryContainer = salonPinkOnPrimaryContainer,
    secondary = salonMintPrimary,
    onSecondary = salonMintOnSecondary,
    secondaryContainer = salonMintSecondaryContainer,
    onSecondaryContainer = salonMintOnSecondaryContainer,
    background = salonBackgroundDark,
    onBackground = Color.White,
    surface = salonSurfaceDark,
    onSurface = Color.White,
    surfaceVariant = salonSurfaceVariant,
    onSurfaceVariant = Color(0xFFCCCCCC),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF)
)

private val LightColorScheme = lightColorScheme(
    primary = salonPinkPrimary,
    onPrimary = salonPinkOnPrimary,
    primaryContainer = salonPinkPrimaryContainer,
    onPrimaryContainer = salonPinkOnPrimaryContainer,
    secondary = salonMintPrimary,
    onSecondary = salonMintOnSecondary,
    secondaryContainer = salonMintSecondaryContainer,
    onSecondaryContainer = salonMintOnSecondaryContainer,
    background = salonBackgroundLight,
    onBackground = salonOnBackground,
    surface = salonSurfaceLight,
    onSurface = salonOnBackground,
    surfaceVariant = salonSurfaceVariant,
    onSurfaceVariant = salonOnSurfaceVariant,
    error = Color(0xFFCF6679),
    onError = Color(0xFF000000)
)

@Composable
fun SalonappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SalonTypography,
        shapes = SalonShapes,
        content = content
    )
}