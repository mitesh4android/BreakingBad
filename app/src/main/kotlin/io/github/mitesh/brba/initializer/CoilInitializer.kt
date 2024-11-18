package io.github.mitesh.brba.initializer

import android.content.Context
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache

class CoilInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Coil.setImageLoader(
            ImageLoader.Builder(context)
                .memoryCache {
                    MemoryCache.Builder(context)
                        .maxSizePercent(0.25)
                        .build()
                }
                .crossfade(true)
//                .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
                .respectCacheHeaders(false)
                .diskCache {
                    DiskCache.Builder()
                        .directory(context.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.10)
                        .build()
                }
                .build(),
        )
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}