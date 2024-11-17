package com.kikii.smarttsassignment.data.repository.di

import android.content.Context
import android.net.ConnectivityManager
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.mapper.AuthMapper
import com.kikii.smarttsassignment.data.mapper.DispatchMapper
import com.kikii.smarttsassignment.data.mapper.RouteMapper
import com.kikii.smarttsassignment.data.repository.composite_repo.auth_dispatch.AuthDispatchRepository
import com.kikii.smarttsassignment.data.repository.composite_repo.auth_dispatch.CompositeAuthDispatchRepository
import com.kikii.smarttsassignment.data.repository.composite_repo.auth_route.AuthRouteRepository
import com.kikii.smarttsassignment.data.repository.composite_repo.auth_route.CompositeAuthRouteRepository
import com.kikii.smarttsassignment.data.repository.default_repo.auth.AuthRepository
import com.kikii.smarttsassignment.data.repository.default_repo.auth.DefaultAuthRepository
import com.kikii.smarttsassignment.data.repository.default_repo.dispatch.DefaultDispatchRepository
import com.kikii.smarttsassignment.data.repository.default_repo.dispatch.DispatchRepository
import com.kikii.smarttsassignment.data.repository.default_repo.route.DefaultRouteRepository
import com.kikii.smarttsassignment.data.repository.default_repo.route.RouteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDefaultDispatchRepository(
        smartTsAssignmentLocalDataSource: SmartTsAssignmentLocalDataSource,
        smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource,
        dispatchMapper: DispatchMapper
    ): DispatchRepository {
        return DefaultDispatchRepository(
            smartTsAssignmentLocalDataSource,
            smartTsAssignmentRemoteDataSource,
            dispatchMapper
        )
    }


    @Provides
    @Singleton
    fun provideCompositeAuthRouteRepository(
        authRepository: AuthRepository,
        routeRepository: RouteRepository,
        connectivityManager: ConnectivityManager
    ): AuthRouteRepository {
        return CompositeAuthRouteRepository(authRepository, routeRepository, connectivityManager)
    }

    @Provides
    @Singleton
    fun provideCompositeAuthDispatchRepository(
        authRepository: AuthRepository,
        dispatchRepository: DispatchRepository,
        connectivityManager: ConnectivityManager
    ): AuthDispatchRepository {
        return CompositeAuthDispatchRepository(authRepository, dispatchRepository, connectivityManager)
    }


    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}