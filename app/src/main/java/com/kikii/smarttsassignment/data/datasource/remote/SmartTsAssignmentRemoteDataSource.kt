package com.kikii.smarttsassignment.data.datasource.remote

import com.kikii.smarttsassignment.data.datasource.remote.api.SmartTsAPIService
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.dispatch.DispatchResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class SmartTsAssignmentRemoteDataSource @Inject constructor(
    private val smartTsAPIService: SmartTsAPIService
) {

    suspend fun getLoginResponse(authRequest: AuthRequest): Flow<Response<AuthResponse>> {
        return flowOf(smartTsAPIService.postGetLoginResponse(authRequest))
    }

    suspend fun getDriverRoute(token : String): Flow<Response<RouteResponse>> {
        return flowOf(smartTsAPIService.getDriverRoute(
            "Bearer $token"
        ))
    }

    suspend fun getDriverDispatch(token : String, date: String): Flow<Response<DispatchResponse>> {
        return flowOf(smartTsAPIService.getDriverDispatch(
            tokenWithPrefix = "Bearer $token",
            date = date))
    }

}