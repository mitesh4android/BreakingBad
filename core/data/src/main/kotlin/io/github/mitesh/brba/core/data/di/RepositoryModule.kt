package io.github.mitesh.brba.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mitesh.brba.core.data.repository.CharactersRepositoryImpl
import io.github.mitesh.brba.core.data.repository.DeviceRepositoryImpl
import io.github.mitesh.brba.core.domain.repository.CharactersRepository
import io.github.mitesh.brba.core.domain.repository.DeviceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(
        repo: CharactersRepositoryImpl,
    ): CharactersRepository

    @Binds
    @Singleton
    abstract fun bindDeviceRepository(
        repo: DeviceRepositoryImpl,
    ): DeviceRepository
}