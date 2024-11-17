package com.kikii.smarttsassignment.ui.feature.auth

import com.kikii.smarttsassignment.data.model.AuthModel

sealed class AuthUiState {
    object Loading : AuthUiState()
    data class Success(val authModel: AuthModel) : AuthUiState()
    data class Error(val exception: Throwable) : AuthUiState()
}