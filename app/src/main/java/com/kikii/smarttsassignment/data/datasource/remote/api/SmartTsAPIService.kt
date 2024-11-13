package com.kikii.smarttsassignment.data.datasource.remote.api

import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.dispatch.DispatchResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface SmartTsAPIService {
    @POST("auth/login")
    suspend fun postGetLoginResponse(
        @Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @GET("route/driver")
    suspend fun getDriverRoute(
        @Header("Authorization") tokenWithPrefix: String
    ): Response<RouteResponse>

    @GET("dispatch/driver/{date}")
    suspend fun getDriverDispatch(
        @Header("Authorization") tokenWithPrefix: String,
        @Field("date") date: String): DispatchResponse
}