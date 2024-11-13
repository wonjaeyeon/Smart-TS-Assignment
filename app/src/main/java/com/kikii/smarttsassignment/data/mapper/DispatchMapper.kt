package com.kikii.smarttsassignment.data.mapper

import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.BusType
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.DispatchEntity
import com.kikii.smarttsassignment.data.datasource.remote.api.dispatch.DispatchResponse
import com.kikii.smarttsassignment.data.model.DispatchModel
import java.text.SimpleDateFormat
import java.util.*

//object DispatchMapper {
//
//    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
//
//    // Convert DispatchResponse to DispatchEntity for local storage
//    fun fromDispatchResponseToDispatchEntity(response: DispatchResponse): List<DispatchEntity> {
//        return response.`object`.map { obj ->
//            DispatchEntity(
//                dispatchId = obj.id ?: 0,
//                driverId = obj.driverId,
//                driverName = obj.driverName,
//                routeId = obj.routeId,
//                routeName = obj.routeName,
//                busId = obj.busId,
//                busNumber = obj.busNumber,
//                busType = BusType.valueOf(obj.busType),  // Assuming BusType is an enum
//                actualStartTime = obj.startTime,
//                actualEndTime = obj.actualEndTime,
//                breakTime = obj.breakTime?.toLong() ?: 0L,
//                startOrder = obj.startOrder.toString(),
//                busRound = obj.busRound.toLong(),
//                driverClass = "DEFAULT",  // Set a default or extract if available
//                passengerList = listOf()  // Adjust based on actual data
//            )
//        }
//    }
//
//    // Convert DispatchEntity to DispatchModel for application use
//    fun fromDispatchEntityToDispatchModel(entity: DispatchEntity): DispatchModel {
//        return DispatchModel(
//            id = entity.dispatchId,
//            startOrder = entity.startOrder.toLong(),
//            routeName = entity.routeName,
//            routeId = entity.routeId,
//            driverName = entity.driverName,
//            driverId = entity.driverId,
//            busId = entity.busId,
//            busNumber = entity.busNumber,
//            busType = entity.busType.name,
//            startTime = dateFormat.format(entity.actualStartTime),
//            endTime = dateFormat.format(entity.actualEndTime),
//            actualEndTime = dateFormat.format(entity.actualEndTime),
//            breakTime = entity.breakTime.toString(),
//            unixStartTime = entity.actualStartTime.time,
//            unixEndTime = entity.actualEndTime.time,
//            busRound = entity.busRound.toInt(),
//            remainEnd = null,  // Placeholder if not available
//            shift = null  // Placeholder if not available
//        )
//    }
//
//    // Convert DispatchResponse directly to DispatchModel
//    fun fromDispatchResponseToDispatchModel(response: DispatchResponse): List<DispatchModel> {
//        return response.`object`.map { obj ->
//            DispatchModel(
//                id = obj.id,
//                startOrder = obj.startOrder,
//                routeName = obj.routeName,
//                routeId = obj.routeId,
//                driverName = obj.driverName,
//                driverId = obj.driverId,
//                busId = obj.busId,
//                busNumber = obj.busNumber,
//                busType = obj.busType,
//                startTime = obj.startTime,
//                endTime = obj.endTime,
//                actualEndTime = obj.actualEndTime,
//                breakTime = obj.breakTime,
//                unixStartTime = obj.unixStartTime,
//                unixEndTime = obj.unixEndTime,
//                busRound = obj.busRound,
//                remainEnd = obj.remainEnd,
//                shift = obj.shift
//            )
//        }
//    }
//
//    // Convert DispatchModel to DispatchEntity for local storage
//    fun fromDispatchModelToDispatchEntity(model: DispatchModel): DispatchEntity {
//        return DispatchEntity(
//            dispatchId = model.id ?: 0,
//            driverId = model.driverId,
//            driverName = model.driverName,
//            routeId = model.routeId,
//            routeName = model.routeName,
//            busId = model.busId,
//            busNumber = model.busNumber,
//            busType = BusType.valueOf(model.busType),  // Assuming BusType is an enum
//            actualStartTime = dateFormat.parse(model.startTime) ?: Date(),
//            actualEndTime = model.endTime?.let { dateFormat.parse(it) } ?: Date(),
//            breakTime = model.breakTime?.toLong() ?: 0L,
//            startOrder = model.startOrder.toString(),
//            busRound = model.busRound.toLong(),
//            driverClass = "DEFAULT",  // Default if no specific data
//            passengerList = listOf()  // Adjust as needed
//        )
//    }
//}

import java.util.*

object DispatchMapper {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    // Convert DispatchResponse to List of DispatchEntity for local storage
    fun fromDispatchResponseToDispatchEntity(response: DispatchResponse): List<DispatchEntity> {
        val dispatchList = response.`object`
            ?: throw IllegalArgumentException("DispatchResponse.object cannot be null")

        return dispatchList.map { dispatch ->
            DispatchEntity(
                id = dispatch.id,
                startOrder = dispatch.startOrder,
                routeName = dispatch.routeName,
                routeId = dispatch.routeId,
                driverName = dispatch.driverName,
                driverId = dispatch.driverId,
                busId = dispatch.busId,
                busNumber = dispatch.busNumber,
                busType = dispatch.busType,  // Assuming BusType is an enum
                startTime = dispatch.startTime,
                endTime = dispatch.endTime,
                actualEndTime = dispatch.actualEndTime,
                breakTime = dispatch.breakTime,
                unixStartTime = dispatch.unixStartTime,
                unixEndTime = dispatch.unixEndTime,
                busRound = dispatch.busRound,
                remainEnd = dispatch.remainEnd,
                shift = dispatch.shift
            )
        }
    }

    // Convert DispatchEntity to DispatchModel for application use
    fun fromDispatchEntityToDispatchModel(entity: DispatchEntity): DispatchModel {
        return DispatchModel(
            id = entity.id,
            startOrder = entity.startOrder,
            routeName = entity.routeName,
            routeId = entity.routeId,
            driverName = entity.driverName,
            driverId = entity.driverId,
            busId = entity.busId,
            busNumber = entity.busNumber,
            busType = entity.busType,
            startTime = entity.startTime,
            endTime = entity.endTime,
            actualEndTime = entity.actualEndTime,
            breakTime = entity.breakTime,
            unixStartTime = entity.unixStartTime,
            unixEndTime = entity.unixEndTime,
            busRound = entity.busRound,
            remainEnd = entity.remainEnd,
            shift = entity.shift
        )
    }

    // Convert DispatchResponse directly to List of DispatchModel
    fun fromDispatchResponseToDispatchModel(response: DispatchResponse): List<DispatchModel> {
        val dispatchList = response.`object`
            ?: throw IllegalArgumentException("DispatchResponse.object cannot be null")

        return dispatchList.map { dispatch ->
            DispatchModel(
                id = dispatch.id,
                startOrder = dispatch.startOrder,
                routeName = dispatch.routeName,
                routeId = dispatch.routeId,
                driverName = dispatch.driverName,
                driverId = dispatch.driverId,
                busId = dispatch.busId,
                busNumber = dispatch.busNumber,
                busType = dispatch.busType,
                startTime = dispatch.startTime,
                endTime = dispatch.endTime,
                actualEndTime = dispatch.actualEndTime,
                breakTime = dispatch.breakTime,
                unixStartTime = dispatch.unixStartTime,
                unixEndTime = dispatch.unixEndTime,
                busRound = dispatch.busRound,
                remainEnd = dispatch.remainEnd,
                shift = dispatch.shift
            )
        }
    }

    // Convert DispatchModel to DispatchEntity for local storage
    fun fromDispatchModelToDispatchEntity(model: DispatchModel): DispatchEntity {
        return DispatchEntity(
            id = model.id,
            startOrder = model.startOrder,
            routeName = model.routeName,
            routeId = model.routeId,
            driverName = model.driverName,
            driverId = model.driverId,
            busId = model.busId,
            busNumber = model.busNumber,
            busType = model.busType,
            startTime = model.startTime,
            endTime = model.endTime,
            actualEndTime = model.actualEndTime,
            breakTime = model.breakTime,
            unixStartTime = model.unixStartTime,
            unixEndTime = model.unixEndTime,
            busRound = model.busRound,
            remainEnd = model.remainEnd,
            shift = model.shift
        )
    }
}