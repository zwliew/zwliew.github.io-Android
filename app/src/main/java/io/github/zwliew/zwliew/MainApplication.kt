package io.github.zwliew.zwliew

import android.app.Application
import androidx.preference.PreferenceManager
import com.squareup.leakcanary.LeakCanary
import io.github.zwliew.zwliew.destinations.settings.THEME_PREF_DEFAULT_VALUE
import io.github.zwliew.zwliew.destinations.settings.THEME_PREF_KEY
import io.github.zwliew.zwliew.util.updateTheme
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Install LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        // TODO
        //LeakCanary.install(this)

        // Install Timber
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        // Set default preferences
        PreferenceManager.setDefaultValues(this, R.xml.preference_settings, false)

        // Set DayNight mode
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val themePrefVal = sharedPrefs.getString(THEME_PREF_KEY, THEME_PREF_DEFAULT_VALUE)
        themePrefVal?.let {
            updateTheme(it)
        }
    }
}