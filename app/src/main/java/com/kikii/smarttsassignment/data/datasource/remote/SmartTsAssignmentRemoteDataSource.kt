package com.kikii.smarttsassignment.data.datasource.remote

import com.kikii.smarttsassignment.data.datasource.remote.api.SmartTsAPIService
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SmartTsAssignmentRemoteDataSource @Inject constructor(
    private val smartTsAPIService: SmartTsAPIService
) {

    suspend fun login(loginRequest: LoginRequest): Flow<LoginResponse> {
        return flowOf(smartTsAPIService.login(loginRequest))
    }

    suspend fun getDriverRoute(): Flow<RouteResponse> {
        return flowOf(smartTsAPIService.getDriverRoute())
    }

    suspend fun getDriverDispatch(date: String): Flow<RouteResponse> {
        return flowOf(smartTsAPIService.getDriverDispatch(date))
    }

}