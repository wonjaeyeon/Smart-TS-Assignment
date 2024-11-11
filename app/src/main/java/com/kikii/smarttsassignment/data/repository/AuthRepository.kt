package com.kikii.smarttsassignment.data.repository

import com.kikii.smarttsassignment.data.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    // login
    suspend fun login(email: String, password: String): Flow<LoginModel?>

    // logout
    suspend fun logout(): Flow<Boolean>

    // jwt
    suspend fun getJwt(): Flow<String?>

}