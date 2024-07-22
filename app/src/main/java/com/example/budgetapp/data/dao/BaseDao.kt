package com.example.budgetapp.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/** Base Data Access Object interface for CRUD operations */
interface BaseDao<T> {
    /** Get all entries */
    fun getAll(): Flow<List<T>>

    /**
     * Get entry by id
     * @param id The id of the entry to get.
     * */
    fun getById(id: Int): Flow<T?>

    /**
     * Insert entry
     * @param entity The entry to insert.
     * */
    @Insert
    suspend fun insert(entity: T)

    /**
     * Update entry
     * @param entity The entry to update.
     * */
    @Update
    suspend fun update(entity: T)

    /**
     * Delete entry
     * @param entity The entry to delete.
     * */
    @Delete
    suspend fun delete(entity: T)
}
