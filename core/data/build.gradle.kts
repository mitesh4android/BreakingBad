plugins {
    id("brba.android.library")
}

android {
    namespace = "io.github.mitesh.brba.core.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
}
