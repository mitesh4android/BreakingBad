package io.github.mitesh.brba.core.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Dispatcher(BrbaDispatcher.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(BrbaDispatcher.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}