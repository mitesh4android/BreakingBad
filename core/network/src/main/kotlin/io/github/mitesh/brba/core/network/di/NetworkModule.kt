package io.github.mitesh.brba.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mitesh.brba.core.network.NetworkDataSource
import io.github.mitesh.brba.core.network.retrofit.RetrofitNetwork

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindNetworkDataSource(
        network: RetrofitNetwork,
    ): NetworkDataSource
}