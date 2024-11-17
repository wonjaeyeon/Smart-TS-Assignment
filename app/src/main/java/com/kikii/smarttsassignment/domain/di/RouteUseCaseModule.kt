package com.kikii.smarttsassignment.domain.di

import com.kikii.smarttsassignment.data.repository.composite_repo.auth_route.AuthRouteRepository
import com.kikii.smarttsassignment.domain.di.coroutine.DefaultDispatcher
import com.kikii.smarttsassignment.domain.usecases.route.FetchRouteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RouteUseCaseModule {

    @Provides
    @Singleton
    fun provideFetchRouteUseCases(
        authRouteRepository: AuthRouteRepository,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ) = FetchRouteUseCases(authRouteRepository, defaultDispatcher)
}