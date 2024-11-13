package com.kikii.smarttsassignment.data.repository.di

import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.mapper.AuthMapper
import com.kikii.smarttsassignment.data.repository.AuthRepository
import com.kikii.smarttsassignment.data.repository.DefaultAuthRepository
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
}