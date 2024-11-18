plugins {
    id("brba.android.library")
}

android {
    namespace = "io.github.mitesh.brba.core.datastore"
}

dependencies {

    implementation(project(":core:model"))

    implementation(libs.androidx.datastore.preferences)
}
