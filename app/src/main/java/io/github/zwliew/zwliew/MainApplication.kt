package io.github.zwliew.zwliew

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}