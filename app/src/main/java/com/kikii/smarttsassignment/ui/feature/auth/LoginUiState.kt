package com.kikii.smarttsassignment.ui.feature.auth

sealed interface LoginUiState {
    object Loading : LoginUiState
    object Success : LoginUiState
    data class Error(val message: String) : LoginUiState
}