package com.kikii.smarttsassignment.data.datasource.local.db.auth

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "auth")
data class AuthEntity (
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,

    @ColumnInfo(name = "loginId")
    val loginId: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "role")
    val role: String,

    @ColumnInfo(name = "userRoleId")
    val userRoleId: Long,

    @ColumnInfo(name = "userRoleName")
    val userRoleName: String,

    @ColumnInfo(name = "token")
    val token: String,

    @ColumnInfo(name = "companyId")
    val companyId: Long,

    @ColumnInfo(name = "companyGroupId")
    val companyGroupId: Long,

    @ColumnInfo(name = "branchId")
    val branchId: Long?,

    @ColumnInfo(name = "branchName")
    val branchName: String?,

    @ColumnInfo(name = "position")
    val position: String,

    @ColumnInfo(name = "companyName")
    val companyName: String,

    @ColumnInfo(name = "deviceToken")
    val deviceToken: String?,

    @ColumnInfo(name = "agree")
    val agree: Int,

    @ColumnInfo(name = "userWorkStandard")
    val userWorkStandard: Int
)