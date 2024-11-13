package com.kikii.smarttsassignment.data.datasource.local.db.route

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RouteDao {

    // Insert a new route, replacing any existing route with the same primary key
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: RouteEntity): Long

    // Update an existing route
    @Update
    suspend fun updateRoute(route: RouteEntity)

    // Delete a specific route
    @Delete
    suspend fun deleteRoute(route: RouteEntity)

    // Get a route by its routeId
    @Query("SELECT * FROM route WHERE routeId = :routeId LIMIT 1")
    suspend fun getRouteById(routeId: Long): RouteEntity?

    // Get all routes for a specific driver
    @Query("SELECT * FROM route WHERE driverId = :driverId")
    suspend fun getRoutesByDriver(driverId: Long): List<RouteEntity>

    // Get all routes by route type
    @Query("SELECT * FROM route WHERE routeType = :routeType")
    suspend fun getRoutesByType(routeType: RouteType): List<RouteEntity>

    // Get all routes by shift
    @Query("SELECT * FROM route WHERE shift = :shift")
    suspend fun getRoutesByShift(shift: ShiftType): List<RouteEntity>

    // Get all routes
    @Query("SELECT * FROM route")
    fun getAllRoutes(): Flow<List<RouteEntity>>
}