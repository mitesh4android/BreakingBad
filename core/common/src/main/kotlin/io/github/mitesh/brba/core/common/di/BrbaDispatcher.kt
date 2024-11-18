package io.github.mitesh.brba.core.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: BrbaDispatcher)

enum class BrbaDispatcher {
    IO,
    MAIN,
}