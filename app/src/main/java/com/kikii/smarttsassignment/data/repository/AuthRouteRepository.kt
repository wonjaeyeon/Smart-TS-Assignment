package com.kikii.smarttsassignment.data.repository

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import kotlinx.coroutines.flow.Flow

interface AuthRouteRepository {
    suspend fun fetchLatestRoute() : Flow<ResultData<RouteModel?>>

    val localRouteModel : RouteRepository


}