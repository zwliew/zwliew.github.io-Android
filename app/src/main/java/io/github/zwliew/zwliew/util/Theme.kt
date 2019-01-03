package io.github.zwliew.zwliew.util

import androidx.appcompat.app.AppCompatDelegate
import io.github.zwliew.zwliew.destinations.settings.ThemeValue

fun updateTheme(themeValue: String) {
    when (themeValue) {
        ThemeValue.LIGHT.value -> AppCompatDelegate.MODE_NIGHT_NO
        ThemeValue.DARK.value -> AppCompatDelegate.MODE_NIGHT_YES
        ThemeValue.AUTO.value -> AppCompatDelegate.MODE_NIGHT_AUTO
        ThemeValue.SYSTEM.value -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        else -> throw IllegalArgumentException()
    }
}