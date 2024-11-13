package com.kikii.smarttsassignment.data.datasource.remote

import com.kikii.smarttsassignment.data.datasource.remote.api.auth.AuthRequest
import com.kikii.smarttsassignment.data.datasource.remote.api.di.SmartTsAPIServiceModule
import org.junit.Assert.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

//
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
//    fun requestToRealServer_2() = runBlocking {
//
//        // Request
//        val response = smartTsAssignmentRemoteDataSource.getLoginResponse(
//            AuthRequest(
//                loginId = "djDEMO",
//                password = "DEMO"
//            )
//        ).first()
//
//        val status = response.status
//
//        // Assert
//        assertEquals(status, 200)
//
//        val message = response.message
//
//        // Assert
//        assertEquals(message, "로그인 성공")
//
//
//        val object_loginId = response.`object`.loginId
//
//        // Assert
//        assertEquals(object_loginId, "djDEMO")
//    }
//
//
//}