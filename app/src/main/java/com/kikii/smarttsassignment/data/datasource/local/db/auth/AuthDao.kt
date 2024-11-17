package com.kikii.smarttsassignment.data.datasource.local.db.auth

import androidx.room.Dao
import androidx.room.Query

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {

    // 1. Create user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: AuthEntity): Long

    // GET user (Just Every user)
    @Query("SELECT * FROM auth")
    fun getUsers(): Flow<List<AuthEntity>>

    @Query("SELECT * FROM auth WHERE loginId = :loginId")
    suspend fun getUser(loginId: String): AuthEntity?

    // 2. Delete user by loginId
    @Query("DELETE FROM auth WHERE loginId = :loginId")
    suspend fun deleteUser(loginId: String)

    @Query("DELETE FROM auth")
    suspend fun deleteAllUsers()

    // 3. Update user info
    @Update
    suspend fun updateUser(user: AuthEntity)

    // 4. Get JWT token by loginId
    @Query("SELECT token FROM auth WHERE loginId = :loginId")
    suspend fun getToken(loginId: String): String?

    // 5. Update only the token field for a specific user
    @Query("UPDATE auth SET token = :token WHERE loginId = :loginId")
    suspend fun updateToken(loginId: String, token: String)
}