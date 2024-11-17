package com.kikii.smarttsassignment.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kikii.smarttsassignment.data.datasource.local.db.auth.AuthDao
import com.kikii.smarttsassignment.data.datasource.local.db.auth.AuthEntity
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.DispatchDao
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.DispatchEntity
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteDao
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteEntity

@Database(
    entities = [
        AuthEntity::class,
        RouteEntity::class,
        DispatchEntity::class,
   ],
    version = 1,
    exportSchema = true
)
@TypeConverters(InstantConverter::class)
abstract class SmartTsDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun routeDao(): RouteDao
    abstract fun dispatchDao(): DispatchDao
}