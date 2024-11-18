import io.github.mitesh.brba.buildlogic.androidExtension
import io.github.mitesh.brba.buildlogic.configureHiltAndroid
import io.github.mitesh.brba.buildlogic.configureKotlinAndroid
import io.github.mitesh.brba.buildlogic.findLibrary

with(pluginManager) {
    apply("com.android.library")
}

androidExtension.apply {
    dependencies {
        add("implementation", findLibrary("timber"))
    }
}

configureKotlinAndroid()
configureHiltAndroid()
