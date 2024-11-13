package com.kikii.smarttsassignment.data.datasource.local

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.db.auth.AuthDao
import com.kikii.smarttsassignment.data.datasource.local.db.auth.AuthEntity
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.DispatchDao
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.DispatchEntity
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteDao
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteEntity
import com.kikii.smarttsassignment.data.model.RouteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SmartTsAssignmentLocalDataSource @Inject constructor(
    private val authDao: AuthDao,
    private val routeDao: RouteDao,
    private val dispatchDao: DispatchDao
) {

    // Create User
    suspend fun createUser(authEntity: AuthEntity) {
        authDao.insertUser(authEntity)
    }

    // Get Users
//    suspend fun getUsers(): List<AuthEntity> {
//        return authDao.getUsers()
//    }
    val currentUsers: Flow<List<AuthEntity>> = authDao.getUsers().map {
        items -> items.map { it }
    }

    // Get User by loginId
    suspend fun getUser(loginId: String): AuthEntity? {
        return authDao.getUser(loginId)
    }

    // Delete User by loginId
    suspend fun deleteUser(loginId: String) {
        authDao.deleteUser(loginId)
    }

    // Delete All Users
    suspend fun deleteAllUsers() {
        authDao.deleteAllUsers()
    }

    // Update User
    suspend fun updateUser(authEntity: AuthEntity) {
        authDao.updateUser(authEntity)
    }

    // Get JWT token by loginId
    suspend fun getToken(loginId: String): String? {
        return authDao.getToken(loginId)
    }

    // Update only the token field for a specific user
    suspend fun updateToken(loginId: String, token: String) {
        authDao.updateToken(loginId, token)
    }


    ////////////////////////////////////////////
    // Route
    ////////////////////////////////////////////
    suspend fun insertRoute(routeEntity: RouteEntity) {
        routeDao.insertRoute(routeEntity)
    }

    suspend fun updateRoute(routeEntity: RouteEntity) {
        routeDao.updateRoute(routeEntity)
    }

    suspend fun deleteRoute(routeEntity: RouteEntity) {
        routeDao.deleteRoute(routeEntity)
    }

    suspend fun getRouteById(routeId: Long): RouteEntity? {
        return routeDao.getRouteById(routeId)
    }

    // get all routes
    val localRoute : Flow<List<RouteEntity>> = routeDao.getAllRoutes()


    // get all routes by driver
    suspend fun getRoutesByDriver(driverId: Long): List<RouteEntity> {
        return routeDao.getRoutesByDriver(driverId)
    }

    ////////////////////////////////////////////
    // Dispatch
    ////////////////////////////////////////////
    suspend fun insertDispatch(dispatchEntity: DispatchEntity): Long {
        return dispatchDao.insertDispatch(dispatchEntity)
    }

    suspend fun insertListOfDispatches(dispatchEntities: List<DispatchEntity>) {
        for (dispatchEntity in dispatchEntities) {
            dispatchDao.insertDispatch(dispatchEntity)
        }
    }

    suspend fun updateDispatch(dispatchEntity: DispatchEntity) {
        dispatchDao.updateDispatch(dispatchEntity)
    }

    suspend fun deleteDispatch(dispatchEntity: DispatchEntity) {
        dispatchDao.deleteDispatch(dispatchEntity)
    }

    // get all
    val allLocalDispatches : Flow<List<DispatchEntity>> = dispatchDao.getAllDispatches()
}