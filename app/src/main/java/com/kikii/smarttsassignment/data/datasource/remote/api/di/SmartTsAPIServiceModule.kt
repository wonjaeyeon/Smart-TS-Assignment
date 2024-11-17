package com.kikii.smarttsassignment.data.datasource.remote.api.di

import com.kikii.smarttsassignment.data.datasource.remote.api.SmartTsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SmartTsAPIServiceModule {

    // KIKII ASSIGNMENT SERVER URL
    private val BASE_URL = "http://168.126.147.134:18080/"

    // LOCALHOST SERVER URL
    //private val BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideSmartTsAPIService(retrofit: Retrofit): SmartTsAPIService {
        return retrofit.create(SmartTsAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}