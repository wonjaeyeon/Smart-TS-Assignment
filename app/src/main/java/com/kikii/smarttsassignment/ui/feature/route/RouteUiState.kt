package com.kikii.smarttsassignment.ui.feature.route

import com.kikii.smarttsassignment.data.model.RouteModel

sealed class RouteUiState {
    object Loading : RouteUiState()
    data class Success(val route: RouteModel) : RouteUiState()
    data class Error(val exception: Throwable) : RouteUiState()
}