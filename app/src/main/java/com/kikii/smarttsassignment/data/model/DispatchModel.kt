package com.kikii.smarttsassignment.data.model

//data class DispatchModel (
//    val id: Long?,
//
//    val startOrder: Long,
//
//    val routeName: String,
//
//    val routeId: Long,
//
//    val driverName: String,
//
//    val driverId: Long,
//
//    val busId: Long,
//
//    val busNumber: String,
//
//    val busType: String,
//
//    val startTime: String,
//
//    val endTime: String?,
//
//    val actualEndTime: String?,
//
//    val breakTime: String?,
//
//    val unixStartTime: Long?,
//
//    val unixEndTime: Long?,
//
//    val busRound: Int,
//
//    val remainEnd: String?,
//
//    val shift: String?
//)
data class DispatchModel(
    val id: Long?,
    val startOrder: Long,
    val routeName: String,
    val routeId: Long,
    val driverName: String,
    val driverId: Long,
    val busId: Long,
    val busNumber: String,
    val busType: String,
    val startTime: String?,
    val endTime: String?,
    val actualEndTime: String?,
    val breakTime: String?,
    val unixStartTime: Long?,
    val unixEndTime: Long?,
    val busRound: Long,
    val remainEnd: String?,
    val shift: String?
)