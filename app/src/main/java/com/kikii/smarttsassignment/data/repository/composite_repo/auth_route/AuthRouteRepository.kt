package com.kikii.smarttsassignment.data.repository.composite_repo.auth_route

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import com.kikii.smarttsassignment.data.repository.default_repo.route.RouteRepository
import kotlinx.coroutines.flow.Flow

interface AuthRouteRepository {
    suspend fun fetchLatestRoute() : Flow<ResultData<RouteModel?>>

    val localRouteModel : Flow<ResultData<RouteModel?>>


}