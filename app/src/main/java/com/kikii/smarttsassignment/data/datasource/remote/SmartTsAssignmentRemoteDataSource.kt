package com.kikii.smarttsassignment.data.datasource.remote

import com.kikii.smarttsassignment.data.datasource.remote.api.SmartTsAPIService
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import javax.inject.Inject

class SmartTsAssignmentRemoteDataSource @Inject constructor(
    private val smartTsAPIService: SmartTsAPIService
) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return smartTsAPIService.login(loginRequest)
    }

    suspend fun getDriverRoute(): RouteResponse {
        return smartTsAPIService.getDriverRoute()
    }

    suspend fun getDriverDispatch(date: String): RouteResponse {
        return smartTsAPIService.getDriverDispatch(date)
    }

}