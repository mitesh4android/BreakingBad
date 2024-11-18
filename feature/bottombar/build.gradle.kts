plugins {
    id("brba.android.feature")
}

android {
    namespace = "io.github.mitesh.brba.feature.bottombar"
}

dependencies {

    implementation(project(":feature:list"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:detail"))
}
