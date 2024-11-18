import io.github.mitesh.brba.buildlogic.configureAndroidCompose

with(pluginManager) {
    apply("brba.android.library")
}

configureAndroidCompose()