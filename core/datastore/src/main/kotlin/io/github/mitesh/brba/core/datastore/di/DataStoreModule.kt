package io.github.mitesh.brba.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private const val PREFERENCES_DEVICE = "PREFERENCES_DEVICE"

    private val Context.deviceDataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCES_DEVICE,
    )

    @Provides
    @Singleton
    fun provideDeviceDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.deviceDataStore
}