package com.example.investidorapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = LightText,
    secondary = AccentTeal,
    onSecondary = LightText,
    tertiary = ContrastDark,
    onTertiary = DarkGray,
    background = DarkBackground,
    onBackground = LightText,
    surface = DarkGray,
    onSurface = LightText
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = OffWhite,
    secondary = AccentTeal,
    onSecondary = DarkGray,
    tertiary = DarkGray,
    onTertiary = OffWhite,
    background = OffWhite,
    onBackground = DarkGray,
    surface = LightGray,
    onSurface = DarkGray
)

@Composable
fun InvestidorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
        typography = Typography,
        content = content
    )
}