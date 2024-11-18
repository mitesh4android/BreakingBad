package io.github.mitesh.brba.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mitesh.brba.core.database.AppDatabase
import io.github.mitesh.brba.core.database.dao.CharacterDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideCharacterDao(
        appDatabase: AppDatabase,
    ): CharacterDao = appDatabase.characterDao()
}