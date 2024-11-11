package com.kikii.smarttsassignment.data.repository

import com.kikii.smarttsassignment.data.model.LoginModel

interface AuthRepository {

    // login
    suspend fun login(email: String, password: String): LoginModel

}