package com.kikii.smarttsassignment.data.repository

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import com.kikii.smarttsassignment.data.mapper.RouteMapper
import com.kikii.smarttsassignment.data.model.RouteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultRouteRepository @Inject constructor(
    private val smartTsAssignmentLocalDataSource: SmartTsAssignmentLocalDataSource,
    private val smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource,
    private val routeMapper: RouteMapper
): RouteRepository{

    override suspend fun fetchLatestRoute(jwt: String): Flow<ResultData<RouteModel?>> {
        return smartTsAssignmentRemoteDataSource.getDriverRoute(jwt).map {
            if (it.isSuccessful) {
                val route = it.body()
                if (route != null) {
                    //ResultData.SuccessData(routeMapper.fromRouteResponseToRouteModel(route))
                    // Map the route response to RouteModel
                    val routeModel = routeMapper.fromRouteResponseToRouteModel(route)

                    // Save the mapped RouteEntity to local database
                    val routeEntity = routeMapper.fromRouteModelToRouteEntity(routeModel)
                    smartTsAssignmentLocalDataSource.insertRoute(routeEntity)  // Assuming this function exists

                    // Return the ResultData with the route model
                    ResultData.SuccessData(routeModel)
                } else {
                    ResultData.ErrorData(Exception("No route found"))
                }
            } else {
                ResultData.ErrorData(Exception("Error fetching route"))
            }
        }
    }

    override val localRouteModel : Flow<ResultData<RouteModel>>
        = smartTsAssignmentLocalDataSource.localRoute.map {
            if (it.isNotEmpty()) {
                ResultData.SuccessData(routeMapper.fromRouteEntityToRouteModel(it.first()))
            } else {
                ResultData.ErrorData(Exception("No route found"))
            }

    }
}