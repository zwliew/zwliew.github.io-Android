package io.github.zwliew.zwliew.routes.settings

// Theme preference
const val THEME_PREF_KEY = "theme"
const val THEME_PREF_DEFAULT_VALUE = "system"

enum class ThemeValue(val value: String) {
    LIGHT("light"),
    DARK("dark"),
    AUTO("auto"),
    SYSTEM("system")
}