package com.kikii.smarttsassignment.data.repository.default_repo.route

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.model.RouteModel
import kotlinx.coroutines.flow.Flow


interface RouteRepository {

    // local cache( = must)
    suspend fun fetchLatestRoute(jwt : String) : Flow<ResultData<RouteModel?>>

    // call from local db
    val localRouteModel : Flow<ResultData<RouteModel>>

}