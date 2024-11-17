package com.kikii.smarttsassignment.data.repository.default_repo.route

import com.kikii.smarttsassignment.data.common.ResultData
import com.kikii.smarttsassignment.data.datasource.local.SmartTsAssignmentLocalDataSource
import com.kikii.smarttsassignment.data.datasource.remote.SmartTsAssignmentRemoteDataSource
import com.kikii.smarttsassignment.data.mapper.RouteMapper
import com.kikii.smarttsassignment.data.model.RouteModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultRouteRepository @Inject constructor(
    private val smartTsAssignmentLocalDataSource: SmartTsAssignmentLocalDataSource,
    private val smartTsAssignmentRemoteDataSource: SmartTsAssignmentRemoteDataSource,
    private val routeMapper: RouteMapper
): RouteRepository {


//    override suspend fun fetchLatestRoute(jwt: String): Flow<ResultData<RouteModel?>> {
//        return flow {
//            try {
//                // Try fetching the latest route from the remote data source
//                val response = smartTsAssignmentRemoteDataSource.getDriverRoute(jwt).first()
//                if (response.isSuccessful) {
//                    val route = response.body()
//                    if (route != null) {
//                        // Map the route response to RouteModel
//                        val routeModel = routeMapper.fromRouteResponseToRouteModel(route)
//
//                        // Save the mapped RouteEntity to local database
//                        val routeEntity = routeMapper.fromRouteModelToRouteEntity(routeModel)
//                        smartTsAssignmentLocalDataSource.insertRoute(routeEntity)  // Assuming this function exists
//
//                        // Emit the ResultData with the fetched route model
//                        emit(ResultData.SuccessData(routeModel))
//                        return@flow
//                    }
//                }
//                // If the response isn't successful or route is null, fall back to local data
//                emit(localRouteModel.first())
//            } catch (e: Exception) {
//                // On error, emit the locally stored route if available
//                emit(localRouteModel.firstOrNull() ?: ResultData.ErrorData(e))
//            }
//        }
//    }


    override suspend fun fetchLatestRoute(jwt: String): Flow<ResultData<RouteModel?>> {
        return flow {
            val response = smartTsAssignmentRemoteDataSource.getDriverRoute(jwt).first()

            if (response.isSuccessful) {
                val route = response.body()
                if (route != null) {
                    val routeModel = routeMapper.fromRouteResponseToRouteModel(route)
                    val routeEntity = routeMapper.fromRouteModelToRouteEntity(routeModel)
                    smartTsAssignmentLocalDataSource.insertRoute(routeEntity)
                    emit(ResultData.SuccessData(routeModel))
                } else {
                    emit(ResultData.ErrorData(Exception("No route response found")))
                }
            } else {
                if (response.body()?.status == 400) {
                    emit(ResultData.ErrorData(Exception("400: Invalid JWT")))
                } else {
                    emit(ResultData.ErrorData(Exception("Error fetching route: ${response.code()} ${response.message()}")))
                }
            }
        }.catch { exception ->
            val localRoute = localRouteModel.firstOrNull()
            if (localRoute is ResultData.SuccessData) {
                emit(localRoute)
            } else {
                emit(ResultData.ErrorData(exception.message?.let { message -> Exception(message) } ?: Exception("Unknown error")))
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