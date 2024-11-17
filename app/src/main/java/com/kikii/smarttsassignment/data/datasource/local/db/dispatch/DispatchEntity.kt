package com.kikii.smarttsassignment.data.datasource.local.db.dispatch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "dispatch")
data class DispatchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null, // Local primary key
    val date : String,
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