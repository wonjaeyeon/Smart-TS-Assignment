package com.kikii.smarttsassignment.ui.feature.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import com.kikii.smarttsassignment.domain.usecases.route.FetchRouteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val fetchRouteUseCases: FetchRouteUseCases
) : ViewModel() {

    // Define a StateFlow for route data
    val routeUiState: StateFlow<RouteUiState> = flow {
        emit(RouteUiState.Loading) // Emit Loading state initially
        fetchRouteUseCases.fetchLatestRoute()
            .collect { result ->
                when (result) {
                    is ResultData.SuccessData -> {
                        result.data?.let {
                            emit(RouteUiState.Success(it))
                        } ?: emit(RouteUiState.Error(Exception("No route data found")))
                    }
                    is ResultData.ErrorData -> {
                        emit(RouteUiState.Error(result.exception))
                    }
                }
            }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RouteUiState.Loading)
}