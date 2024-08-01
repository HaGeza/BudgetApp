package com.example.budgetapp.domain.repository

import com.example.budgetapp.domain.model.Account
import com.example.budgetapp.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

/**
 * Interface for a generic repository
 * @param <T> the type of entity the repository handles
 */
interface Repository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Int): Flow<T?>

    suspend fun insert(entry: T)
    suspend fun update(entry: T)
    suspend fun delete(entry: T)
}

/** Interface for handling [Account] entities */
interface AccountsRepository : Repository<Account>

/** Interface for handling [ExchangeRate] entities */
interface ExchangeRatesRepository : Repository<ExchangeRate>
