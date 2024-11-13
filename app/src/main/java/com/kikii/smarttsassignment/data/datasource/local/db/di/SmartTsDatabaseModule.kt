package com.kikii.smarttsassignment.data.datasource.local.db.di

import android.content.Context
import androidx.room.Room
import com.kikii.smarttsassignment.data.datasource.local.db.SmartTsDatabase
import com.kikii.smarttsassignment.data.datasource.local.db.auth.AuthDao
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.DispatchDao
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SmartTsDatabaseModule {

    // Auth
    @Provides
    fun provideAuthDao(smartTsDatabase: SmartTsDatabase) : AuthDao {
        return smartTsDatabase.authDao()
    }

    @Provides
    fun provideRouteDao(smartTsDatabase: SmartTsDatabase) : RouteDao {
        return smartTsDatabase.routeDao()
    }

    @Provides
    fun provideDispatchDao(smartTsDatabase: SmartTsDatabase) : DispatchDao {
        return smartTsDatabase.dispatchDao()
    }

    @Provides
    @Singleton
    fun provideSmartTsDatabase(@ApplicationContext appContext: Context): SmartTsDatabase {
        return Room.databaseBuilder(
            appContext,
            SmartTsDatabase::class.java,
            "SmartTsDatabase"
        ).build()
    }
}