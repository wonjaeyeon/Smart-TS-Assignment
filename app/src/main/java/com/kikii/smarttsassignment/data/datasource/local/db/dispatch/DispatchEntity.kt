package com.kikii.smarttsassignment.data.datasource.local.db.dispatch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "dispatch")
data class DispatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "id")
    val dispatchId: Long,

    @ColumnInfo(name = "driverId")
    val driverId: Long,

    @ColumnInfo(name = "driverName")
    val driverName: String,

    @ColumnInfo(name = "routeId")
    val routeId: Long,

    @ColumnInfo(name = "routeName")
    val routeName: String,

    @ColumnInfo(name = "busId")
    val busId: Long,

    @ColumnInfo(name = "busNumber")
    val busNumber: String,

    @ColumnInfo(name = "busType")
    val busType: Enum<BusType>,

    @ColumnInfo(name = "actualStartTime")
    val actualStartTime: Date,

    @ColumnInfo(name = "actualEndTime")
    val actualEndTime: Date,

    @ColumnInfo(name = "breakTime")
    val breakTime: Long,

    @ColumnInfo(name = "startOrder")
    val startOrder: String,

    @ColumnInfo(name = "busRound")
    val busRound: Long,

    @ColumnInfo(name = "driverClass")
    val driverClass: String,

    @ColumnInfo(name = "passengerList")
    val passengerList: List<String>

    )