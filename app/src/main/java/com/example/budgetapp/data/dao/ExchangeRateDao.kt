package com.example.budgetapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgetapp.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

/** Data Access Objecct for the exchange rates table */
@Dao
interface ExchangeRateDao : BaseDao<ExchangeRate> {
    /** Get all exchange rates */
    @Query("SELECT * FROM exchange_rates")
    override fun getAll(): Flow<List<ExchangeRate>>

    /**
     * Get an exchange rate by id
     * @param id The id of the exchange rate to get
     */
    @Query("SELECT * FROM exchange_rates WHERE id = :id")
    override fun getById(id: Int): Flow<ExchangeRate?>

    /**
     * Get an exchange rate by the source currency
     * @param source The source currency of the exchange rate to get
     * @param other The other currency of the exchange rate to get
     */
    @Query("SELECT * FROM exchange_rates WHERE source = :source AND other = :other")
    fun getByCurrencies(source: String, other: String): Flow<ExchangeRate?>

    @Insert
    suspend fun insertAll(exchangeRates: List<ExchangeRate>)
}
