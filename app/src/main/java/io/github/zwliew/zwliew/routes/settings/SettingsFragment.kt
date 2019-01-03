package io.github.zwliew.zwliew.routes.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.updateTheme

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_settings, rootKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val themePreference = findPreference<ListPreference>(THEME_PREF_KEY)
        themePreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                updateTheme(newValue as String)
                activity?.recreate()
                true
            }

        return view
    }
}