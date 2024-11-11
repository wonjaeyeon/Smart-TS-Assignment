package com.kikii.smarttsassignment.data.datasource.remote.api

import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.auth.LoginResponse
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface SmartTsAPIService {
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("route/driver")
    suspend fun getDriverRoute(
        //@Header("Bearer") token: String
    ): RouteResponse

    @GET("dispatch/driver/{date}")
    suspend fun getDriverDispatch(
        //@Header("Bearer") token: String,
        @Field("date") date: String): RouteResponse

}