package com.kikii.smarttsassignment.domain.di.coroutine

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    @Singleton
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @DefaultDispatcher
    @Provides
    @Singleton
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}