plugins {
    id("brba.android.library")
}

android {
    namespace = "io.github.mitesh.brba.core.domain"
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
