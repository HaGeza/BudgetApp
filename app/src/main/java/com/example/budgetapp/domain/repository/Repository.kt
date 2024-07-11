package com.example.budgetapp.domain.repository

import com.example.budgetapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Int): Flow<T>

    suspend fun insert(entry: T)
    suspend fun update(entry: T)
    suspend fun delete(entry: T)
}

interface AccountsRepository : Repository<Account>
