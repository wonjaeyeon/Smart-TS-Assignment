package com.kikii.smarttsassignment.data.repository.default_repo.auth

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.AuthModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    // login
    suspend fun login(email: String, password: String): Flow<ResultData<AuthModel?>>

    // logout
    suspend fun logout(): Flow<Boolean>

    // get auth
    //suspend fun getAuthModel(): Flow<ResultData<AuthModel?>>
    val currentAuthModel: Flow<ResultData<AuthModel>>

    // update auth
    suspend fun updateAuthModel(): Flow<ResultData<AuthModel?>>

    // jwt
    suspend fun getJwt(): Flow<String?>

}