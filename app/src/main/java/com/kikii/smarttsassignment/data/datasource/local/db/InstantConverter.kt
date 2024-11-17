package com.kikii.smarttsassignment.data.datasource.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kikii.smarttsassignment.data.datasource.local.db.dispatch.BusType
import com.kikii.smarttsassignment.data.datasource.local.db.route.ShiftType
import java.util.*

class InstantConverter {

    private val gson = Gson()


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // Convert BusType enum to a String
    @TypeConverter
    fun fromBusType(busType: BusType?): String? {
        return busType?.name
    }

    // Convert String back to BusType enum
    @TypeConverter
    fun toBusType(busType: String?): BusType? {
        return busType?.let { BusType.valueOf(it) }
    }

    // Converters ShiftType enum to a String
    @TypeConverter
    fun fromShiftType(shiftType: ShiftType?): String? {
        return shiftType?.name
    }

    // Convert String back to ShiftType enum
    @TypeConverter
    fun toShiftType(shiftType: String?): ShiftType? {
        return shiftType?.let { ShiftType.valueOf(it) }
    }

    // List<String> Converters
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(it, listType)
        }
    }

}