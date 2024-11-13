package com.kikii.smarttsassignment.data.repository.di

import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.mapper.AuthMapper
import com.kikii.smarttsassignment.data.mapper.RouteMapper
import com.kikii.smarttsassignment.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SmartTsRepositoryModule {

    @Provides
    @Singleton
    fun provideDefaultAuthRepository(
        smartTsAssignmentLocalDataSource: SmartTsAssignmentLocalDataSource,
        smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource,
        authMapper: AuthMapper
    ): AuthRepository {
        return DefaultAuthRepository(
            smartTsAssignmentLocalDataSource,
            smartTsAssignmentRemoteDataSource,
            authMapper
        )
    }

    @Provides
    @Singleton
    fun provideDefaultRouteRepository(
        smartTsAssignmentLocalDataSource: SmartTsAssignmentLocalDataSource,
        smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource,
        routeMapper : RouteMapper
    ): RouteRepository {
        return DefaultRouteRepository(
            smartTsAssignmentLocalDataSource,
            smartTsAssignmentRemoteDataSource,
            routeMapper
        )
    }

    @Provides
    @Singleton
    fun provideAuthRouteRepository(
        authRepository: AuthRepository,
        routeRepository: RouteRepository
    ): AuthRouteRepository {
        return CompositeAuthRouteRepository(authRepository, routeRepository)
    }
}