package com.kikii.smarttsassignment.data.datasource.local.db.dispatch

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DispatchDao {

    // Insert new dispatch or replace if a conflict occurs on specified fields
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDispatch(dispatch: DispatchEntity): Long

    // Update an existing dispatch entry
    @Update
    suspend fun updateDispatch(dispatch: DispatchEntity)

    // Delete a specific dispatch by its primary key ID
    @Delete
    suspend fun deleteDispatch(dispatch: DispatchEntity)

    // Delete all dispatch entries
    @Query("DELETE FROM dispatch")
    suspend fun deleteAllDispatches()

    // Get a dispatch by driverId, routeId, and startOrder to find duplicates
    @Query("SELECT * FROM dispatch WHERE driverId = :driverId AND routeId = :routeId AND startOrder = :startOrder LIMIT 1")
    suspend fun findDuplicateDispatch(driverId: Long, routeId: Long, startOrder: Long): DispatchEntity?

    @Query("SELECT * FROM dispatch WHERE date = :date")
    suspend fun findDispatchesByDate(date: String): List<DispatchEntity>

    // Upsert logic: check for duplicates and replace if necessary
    @Transaction
    suspend fun upsertDispatch(dispatch: DispatchEntity) {
        val duplicate = findDuplicateDispatch(dispatch.driverId, dispatch.routeId, dispatch.startOrder)
        if (duplicate != null) {
            // Replace by deleting the old entry and inserting the new one
            deleteDispatch(duplicate)
        }
        insertDispatch(dispatch)
    }

    // Retrieve all dispatch entries
    @Query("SELECT * FROM dispatch")
    fun getAllDispatches(): Flow<List<DispatchEntity>>
}