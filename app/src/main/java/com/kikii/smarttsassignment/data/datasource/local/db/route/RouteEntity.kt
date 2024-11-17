package com.kikii.smarttsassignment.data.datasource.local.db.route

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.BusType
import java.util.*

@Entity(tableName = "route")
data class RouteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "shift")
    val shift: ShiftType,

    @ColumnInfo(name = "driverId")
    val driverId: Long,

    @ColumnInfo(name = "driverName")
    val driverName: String,

    @ColumnInfo(name = "routeId")
    val routeId: Long,

    @ColumnInfo(name = "routeName")
    val routeName: String,

    @ColumnInfo(name = "routeType")
    val routeType: RouteType,

    @ColumnInfo(name = "busId")
    val busId: Long,

    @ColumnInfo(name = "busNumber")
    val busNumber: String,

    @ColumnInfo(name = "busType")
    val busType: BusType,

    @ColumnInfo(name = "branchId")
    val branchId: Long,

    @ColumnInfo(name = "branchName")
    val branchName: String,

    @ColumnInfo(name = "updatedManagerName")
    val updatedManagerName: String,

    @ColumnInfo(name = "createdAt")
    val createdAt: Date,

    @ColumnInfo(name = "updatedAt")
    val updatedAt: Date,

    @ColumnInfo(name = "reason")
    val reason: String
)