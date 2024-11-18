package io.github.mitesh.brba.initializer

import android.content.Context
import androidx.startup.Initializer
import io.github.mitesh.brba.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (!io.github.mitesh.brba.BuildConfig.DEBUG) return
        Timber.plant(Timber.DebugTree())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}