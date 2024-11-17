package com.kikii.smarttsassignment.domain.di

import com.kikii.smarttsassignment.domain.usecases.date.DateFormatUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateUseCaseModule {

    @Provides
    @Singleton
    fun provideDateFormatUseCases() = DateFormatUseCases()
}