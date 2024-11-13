package com.kikii.smarttsassignment.domain.di

import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.domain.di.coroutine.DefaultDispatcher
import com.kikii.smarttsassignment.domain.usecases.auth.GetAuthUseCases
import com.kikii.smarttsassignment.domain.usecases.auth.LoginAuthUseCases
import com.kikii.smarttsassignment.domain.usecases.auth.LogoutAuthUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {

    @Provides
    @Singleton
    fun provideGetAuthUseCases(
        authRepository: AuthRepository
    ) = GetAuthUseCases(authRepository)

    @Provides
    @Singleton
    fun provideLoginAuthUseCases(
        authRepository: AuthRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) = LoginAuthUseCases(authRepository, defaultDispatcher)

    @Provides
    @Singleton
    fun provideLogoutAuthUseCases(
        authRepository: AuthRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) = LogoutAuthUseCases(authRepository, defaultDispatcher)
}