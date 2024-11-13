package com.kikii.smarttsassignment.data.mapper.di

import com.kikii.smarttsassignment.data.mapper.AuthMapper
import com.kikii.smarttsassignment.data.mapper.RouteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideAuthMapper(): AuthMapper {
        return AuthMapper
    }

    @Provides
    @Singleton
    fun provideRouteMapper(): RouteMapper {
        return RouteMapper
    }
}