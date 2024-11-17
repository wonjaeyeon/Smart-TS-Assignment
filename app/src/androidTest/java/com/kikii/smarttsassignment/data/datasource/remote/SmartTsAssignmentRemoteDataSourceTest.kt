package com.kikii.smarttsassignment.data.datasource.remote

import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.di.SmartTsAPIServiceModule
import org.junit.Assert.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


//class SmartTsAssignmentRemoteDataSourceTest {
//
//    private lateinit var smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource
//
//    @Before
//    fun setUp() {
//        val realAPIService = SmartTsAPIServiceModule.provideSmartTsAPIService(
//            SmartTsAPIServiceModule.provideRetrofit()
//
//
//        )
//        smartTsAssignmentRemoteDataSource = SmartTsAssignmentRemoteDataSource(realAPIService)
//    }
//
//    @Test
//    fun requestToRealServer() = runBlocking {
//
//        // Request
//        val response = smartTsAssignmentRemoteDataSource.getLoginResponse(
//            AuthRequest(
//                loginId = "djDEMO",
//                password = "DEMO"
//            )
//        ).first()
//
//        // Assert
//        assertNotNull(response)
//    }
//
//
//    @Test
//    fun testRouteResponse() = runBlocking {
//        // Given
//        val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1ODM1IiwiaXNzIjoia2lraUIiLCJpYXQiOjE3MzE0NzE2ODAsImV4cCI6MTczMjA3NjQ4MH0.4D_L07WQPJvEQwZcxtN9UiFtz8bAty95hRGBx9spOhGK5RGIYD56frNxWVguj9s4ILN9_QJFddmVOGcDtnrAqw"
//
//        // When
//        val response = smartTsAssignmentRemoteDataSource.getDriverRoute(token).firstOrNull()
//
//        println(
//            "Route response: ${response?.body()}"
//        )
//
//        // Then
//        assertNotNull(response)
//
//        assert(response?.body() != null)
//
//        assert(response?.body()!!.status == 200)
//        println("Route response: ${response.body()}")
//    }
//
//
//}