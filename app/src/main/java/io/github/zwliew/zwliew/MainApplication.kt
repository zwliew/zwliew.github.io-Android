package io.github.zwliew.zwliew

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}