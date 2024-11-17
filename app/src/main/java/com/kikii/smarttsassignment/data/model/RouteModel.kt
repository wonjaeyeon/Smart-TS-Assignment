package com.kikii.smarttsassignment.data.model

data class  RouteModel (
    val id: Long,

    val shift: String,

    val driverId: Long,

    val driverName: String,

    val routeId: Long,

    val routeName: String,

    val routeType: String,

    val busId: Long,

    val busNumber: String,

    val busType: String,

    val branchId: Long,

    val branchName: String,

    val updatedManagerName: String,

    val createdAt: String,

    val updatedAt: String,

    val reason: String?
)