package com.kikii.smarttsassignment.data.mapper

import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.BusType
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteEntity
import com.kikii.smarttsassignment.data.datasource.local.db.route.RouteType
import com.kikii.smarttsassignment.data.datasource.local.db.route.ShiftType
import com.kikii.smarttsassignment.data.datasource.remote.api.route.RouteResponse
import com.kikii.smarttsassignment.data.model.RouteModel
import java.text.SimpleDateFormat
import java.util.*

object RouteMapper {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    // Convert RouteResponse to RouteEntity for local storage
    fun fromRouteResponseToRouteEntity(response: RouteResponse): RouteEntity {
        val obj = response.`object`
            ?: throw IllegalArgumentException("RouteResponse.object cannot be null")
        return RouteEntity(
            id = obj.id,
            shift = ShiftType.valueOf(obj.shift),  // Assuming ShiftType is an enum
            driverId = obj.driverId,
            driverName = obj.driverName,
            routeId = obj.routeId,
            routeName = obj.routeName,
            routeType = RouteType.valueOf(obj.routeType),  // Assuming RouteType is an enum
            busId = obj.busId,
            busNumber = obj.busNumber,
            busType = BusType.valueOf(obj.busType),  // Assuming BusType is an enum
            branchId = obj.branchId,
            branchName = obj.branchName,
            updatedManagerName = obj.updatedManagerName,
            createdAt = dateFormat.parse(obj.createdAt) ?: Date(),
            updatedAt = dateFormat.parse(obj.updatedAt) ?: Date(),
            reason = obj.reason ?: ""
        )
    }

    // Convert RouteEntity to RouteModel for application use
    fun fromRouteEntityToRouteModel(entity: RouteEntity): RouteModel {
        return RouteModel(
            id = entity.id,
            shift = entity.shift.name,
            driverId = entity.driverId,
            driverName = entity.driverName,
            routeId = entity.routeId,
            routeName = entity.routeName,
            routeType = entity.routeType.name,
            busId = entity.busId,
            busNumber = entity.busNumber,
            busType = entity.busType.name,
            branchId = entity.branchId,
            branchName = entity.branchName,
            updatedManagerName = entity.updatedManagerName,
            createdAt = dateFormat.format(entity.createdAt),
            updatedAt = dateFormat.format(entity.updatedAt),
            reason = entity.reason
        )
    }

    // Convert RouteResponse directly to RouteModel
    fun fromRouteResponseToRouteModel(response: RouteResponse): RouteModel {
        val obj = response.`object`
            ?: throw IllegalArgumentException("RouteResponse.object cannot be null")
        return RouteModel(
            id = obj.id,
            shift = obj.shift,
            driverId = obj.driverId,
            driverName = obj.driverName,
            routeId = obj.routeId,
            routeName = obj.routeName,
            routeType = obj.routeType,
            busId = obj.busId,
            busNumber = obj.busNumber,
            busType = obj.busType,
            branchId = obj.branchId,
            branchName = obj.branchName,
            updatedManagerName = obj.updatedManagerName,
            createdAt = obj.createdAt,
            updatedAt = obj.updatedAt,
            reason = obj.reason
        )
    }

    // Convert RouteModel to RouteEntity for local storage
    fun fromRouteModelToRouteEntity(model: RouteModel): RouteEntity {
        return RouteEntity(
            id = model.id,
            shift = ShiftType.valueOf(model.shift),
            driverId = model.driverId,
            driverName = model.driverName,
            routeId = model.routeId,
            routeName = model.routeName,
            routeType = RouteType.valueOf(model.routeType),
            busId = model.busId,
            busNumber = model.busNumber,
            busType = BusType.valueOf(model.busType),
            branchId = model.branchId,
            branchName = model.branchName,
            updatedManagerName = model.updatedManagerName,
            createdAt = dateFormat.parse(model.createdAt) ?: Date(),
            updatedAt = dateFormat.parse(model.updatedAt) ?: Date(),
            reason = model.reason ?: ""
        )
    }
}